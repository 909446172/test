package demo.face.plus.faceplus.face;

/**
 * Created by Jason on 2017/12/21. Face++人脸识别技术
 */
public interface FaceUrl {
	// 人脸识别接口，face++类型
	String face_interface_type = "faceplus_type";

	// 人脸检测
	String url_face_detection = "https://api-cn.faceplusplus.com/facepp/v3/detect";

	// 查询所有faceset
	String url_faceset_get = "https://api-cn.faceplusplus.com/facepp/v3/faceset/getfacesets";

	// 创建单个faceset
	String url_faceset_create = "https://api-cn.faceplusplus.com/facepp/v3/faceset/create";

	// 查询单个faceset 的详细信息
	String url_faceset_detail = "https://api-cn.faceplusplus.com/facepp/v3/faceset/getdetail";

	// 将faceset_token 添加到faceset
	String url_facesetAdd_faceToken = "https://api-cn.faceplusplus.com/facepp/v3/faceset/addface";

	// 根据face_token，分析得出人脸关键点，人脸属性信息。一次调用最多支持分析 5 个人脸。
	String url_face_analyze = "https://api-cn.faceplusplus.com/facepp/v3/face/analyze";

	// 匹配相似度
	String url_face_search = "https://api-cn.faceplusplus.com/facepp/v3/search";

	// 删除faceSet中单个faceId
	String url_face_removeface = "https://api-cn.faceplusplus.com/facepp/v3/faceset/removeface";

	//删除一个人脸集合
	String url_face_delete = "https://api-cn.faceplusplus.com/facepp/v3/faceset/delete";

	//异步添加face到faceset中
	String url_face_async_addface = "https://api-cn.faceplusplus.com/facepp/v3/faceset/async/addface";
}
