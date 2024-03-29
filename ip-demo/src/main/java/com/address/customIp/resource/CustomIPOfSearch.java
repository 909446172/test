package com.address.customIp.resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class CustomIPOfSearch {


    public static final int BTREE_ALGORITHM  = 1;
    public static final int BINARY_ALGORITHM = 2;
    public static final int MEMORY_ALGORITYM = 3;

    /**
     * db config
     */
    private  DbConfig dbConfig = null;

    /**
     * db file access handler
     */
    private static RandomAccessFile raf = null;

    /**
     * header blocks buffer
     */
    private long[] HeaderSip = null;
    private int[]  HeaderPtr = null;
    private int headerLength;

    /**
     * super blocks info
     */
    private long firstIndexPtr = 0;
    private long lastIndexPtr = 0;
    private int totalIndexBlocks = 0;

    /**
     * for memory mode
     * the original db binary string
     */
    private byte[] dbBinStr = null;
    private static  byte[] buf = null;

    public static  String dbFile ;

    /**
     * construct class
     *
     * @param   bdConfig
     * @param   dbFile
     * @throws  FileNotFoundException
     */
    public CustomIPOfSearch(DbConfig dbConfig,byte[] buf) throws FileNotFoundException
    {

        this.dbConfig = dbConfig;
        this.buf = buf;


    }




    /**
     * get the region with a int ip address with b-tree algorithm
     *
     * @param   ip
     * @throws  IOException
     */
    public DataBlock btreeSearch(long ip ) throws IOException
    {

        byte[] b = buf;
        //check and load the header
        if ( HeaderSip == null )  {
            // byte[] b = new byte[4096];

            //fill the header
            int len = b.length >> 3, idx = 0;  //b.lenght / 8
            HeaderSip = new long[len];     //获得长度可以装下全部的ip值
            HeaderPtr = new int [len];
            long startIp, dataPtr;
            for ( int i = 0; i < b.length; i += 8 ) {
                startIp = Util.getIntLong(b, i);
                dataPtr = Util.getIntLong(b, i + 4);
                if ( dataPtr == 0 ) break;

                HeaderSip[idx] = startIp;
                HeaderPtr[idx] = (int)dataPtr;
                idx++;
            }   //将全部的ip值放入 ip值数组

            headerLength = idx;  //位全部ip值数组的长度
        }

        //1. define the index block with the binary search
        if ( ip == HeaderSip[0] ) {      //如果ip等于第一个值那么返回结果
            return getByIndexPtr(HeaderPtr[0]);
        } else if ( ip == HeaderSip[headerLength-1] ) {   //如果ip等于最后一个值返回结果
            return getByIndexPtr(HeaderPtr[headerLength-1]);
        }

        int l = 0, h = headerLength, sptr = 0, eptr = 0;
        while ( l <= h ) {    //遍历全部的ip值
            int m = (l + h) >> 1;

            //perfetc matched, just return it
            if ( ip == HeaderSip[m] ) {  //如果ip值等于中间值
                if ( m > 0 ) {           //if(m>0) 那么直接得到结果
                    sptr = HeaderPtr[m-1];
                    eptr = HeaderPtr[m  ];
                } else {
                    sptr = HeaderPtr[m ];
                    eptr = HeaderPtr[m+1];
                }

                break;
            }

            //less then the middle value
            if ( ip < HeaderSip[m] ) {  //如果ip 小于第一个ip的值
                if ( m == 0 ) {
                    sptr = HeaderPtr[m  ];
                    eptr = HeaderPtr[m+1];
                    break;
                } else if ( ip > HeaderSip[m-1] ) {
                    sptr = HeaderPtr[m-1];
                    eptr = HeaderPtr[m  ];
                    break;
                }
                h = m - 1;
            } else {
                if ( m == headerLength - 1 ) {
                    sptr = HeaderPtr[m-1];
                    eptr = HeaderPtr[m  ];
                    break;
                } else if ( ip <= HeaderSip[m+1] ) {
                    sptr = HeaderPtr[m  ];
                    eptr = HeaderPtr[m+1];
                    break;
                }
                l = m + 1;
            }
        }

        //match nothing just stop it
        if ( sptr == 0 ) return null;

        //2. search the index blocks to define the data
        int blockLen = eptr - sptr, blen = IndexBlock.getIndexBlockLength();
         int  blo = blockLen + blen;    //include the right border block

        byte[] iBuffer = Arrays.copyOfRange(b, sptr, sptr + blo);
        l = 0; h = blockLen / blen;
        long sip, eip, dataptr = 0;
        while ( l <= h ) {
            int m = (l + h) >> 1;
            int p = m * blen;
            sip = Util.getIntLong(iBuffer, p);
            if ( ip < sip ) {
                h = m - 1;
            } else {
                eip = Util.getIntLong(iBuffer, p + 4);
                if ( ip > eip ) {
                    l = m + 1;
                } else {
                    dataptr = Util.getIntLong(iBuffer, p + 8);
                    break;
                }
            }
        }

        //not matched
        if ( dataptr == 0 ) return null;

        //3. get the data
        int dataLen = (int)((dataptr >> 24) & 0xFF);
        int dataPtr = (int)((dataptr & 0x00FFFFFF));

        byte[] data = new byte[dataLen];
        data = Arrays.copyOfRange(b, dataPtr, dataPtr + data.length);

        int city_id = (int) Util.getIntLong(data, 0);
        String region = new String(data, 4, data.length - 4, "UTF-8");

        return new DataBlock(city_id, region, dataPtr);
    }

    /**
     * get the region throught the ip address with b-tree search algorithm
     *
     * @param   ip
     * @return  DataBlock
     * @throws  IOException
     */
    public DataBlock btreeSearch(String ip ) throws IOException
    {
        return btreeSearch(Util.ip2long(ip));
    }


    public DataBlock binarySearch(String ip ) throws IOException
    {
      return    binarySearch( Util.ip2long(ip) );
    }

    public DataBlock binarySearch(long ip ) throws IOException
    {
        int blen = IndexBlock.getIndexBlockLength();
        if ( totalIndexBlocks == 0 ) {
            byte[] superBytes = new byte[8];
            superBytes = Arrays.copyOfRange(buf, 0, superBytes.length);


            //initialize the global vars
            firstIndexPtr = Util.getIntLong(superBytes, 0);
            lastIndexPtr = Util.getIntLong(superBytes, 4);
            totalIndexBlocks = (int)((lastIndexPtr - firstIndexPtr)/blen) + 1;
        }

        //search the index blocks to define the data
        int l = 0, h = totalIndexBlocks;
        byte[] buffer = new byte[blen];
        long sip, eip, dataptr = 0;
        while ( l <= h ) {
            int m = (l + h) >> 1;
            buffer = Arrays.copyOfRange(buf, (int) (firstIndexPtr + m * blen), (int) (firstIndexPtr + m * blen) + buffer.length);

            sip = Util.getIntLong(buffer, 0);
            if ( ip < sip ) {
                h = m - 1;
            } else {
                eip = Util.getIntLong(buffer, 4);
                if ( ip > eip ) {
                    l = m + 1;
                } else {
                    dataptr = Util.getIntLong(buffer, 8);
                    break;
                }
            }
        }

        //not matched
        if ( dataptr == 0 ) return null;

        //get the data
        int dataLen = (int)((dataptr >> 24) & 0xFF);
        int dataPtr = (int)((dataptr & 0x00FFFFFF));

        byte[] data = new byte[dataLen];
        data = Arrays.copyOfRange(buf, dataPtr, dataPtr + data.length);

        int city_id = (int) Util.getIntLong(data, 0);
        String region = new String(data, 4, data.length - 4, "UTF-8");

        return new DataBlock(city_id, region, dataPtr);
    }




    /**
     * get by index ptr
     *
     * @param   indexPtr
     * @throws  IOException
     */
    public DataBlock getByIndexPtr(long ptr ) throws IOException
    {
        raf.seek(ptr);
        byte[] buffer = new byte[12];
        raf.readFully(buffer, 0, buffer.length);
        //long startIp = Util.getIntLong(buffer, 0);
        //long endIp = Util.getIntLong(buffer, 4);
        long extra = Util.getIntLong(buffer, 8);

        int dataLen = (int)((extra >> 24) & 0xFF);
        int dataPtr = (int)((extra & 0x00FFFFFF));

        raf.seek(dataPtr);
        byte[] data = new byte[dataLen];
        raf.readFully(data, 0, data.length);

        int city_id = (int) Util.getIntLong(data, 0);
        String region = new String(data, 4, data.length - 4, "UTF-8");

        return new DataBlock(city_id, region, dataPtr);
    }



    public DataBlock memorySearch(long ip) throws IOException
    {
        int blen = IndexBlock.getIndexBlockLength();
        if ( dbBinStr == null ) {
            dbBinStr = buf;

            //initialize the global vars
            firstIndexPtr    = Util.getIntLong(dbBinStr, 0);
            lastIndexPtr     = Util.getIntLong(dbBinStr, 4);
            totalIndexBlocks = (int)((lastIndexPtr - firstIndexPtr)/blen) + 1;
        }

        //search the index blocks to define the data
        int l = 0, h = totalIndexBlocks;
        long sip, eip, dataptr = 0;
        while ( l <= h ) {
            int m = (l + h) >> 1;
            int p = (int)(firstIndexPtr + m * blen);

            sip = Util.getIntLong(dbBinStr, p);
            if ( ip < sip ) {
                h = m - 1;
            } else {
                eip = Util.getIntLong(dbBinStr, p + 4);
                if ( ip > eip ) {
                    l = m + 1;
                } else {
                    dataptr = Util.getIntLong(dbBinStr, p + 8);
                    break;
                }
            }
        }

        //not matched
        if ( dataptr == 0 ) return null;

        //get the data
        int dataLen = (int)((dataptr >> 24) & 0xFF);
        int dataPtr = (int)((dataptr & 0x00FFFFFF));
        int city_id = (int) Util.getIntLong(dbBinStr, dataPtr);
        String region = new String(dbBinStr, dataPtr + 4, dataLen - 4, "UTF-8");

        return new DataBlock(city_id, region, dataPtr);
    }

    /**
     * get the region throught the ip address with memory binary search algorithm
     *
     * @param   ip
     * @return  DataBlock
     * @throws  IOException
     */
    public DataBlock memorySearch(String ip ) throws IOException
    {
        return memorySearch(Util.ip2long(ip));
    }


}
