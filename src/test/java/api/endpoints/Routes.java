package api.endpoints;
/*
 Swagger URI -->https://petstore.swagger.io
Create User(POST): https://petstore.swagger.io/v2/user
Get User(GET): https://petstore.swagger.io/v2/user/{username}
Update User(PUT): https://petstore.swagger.io/v2/user/{username}
Delete User(DELETE): https://petstore.swagger.io/v2/user/{username}
*/

public class Routes {
	public static String base_url= "https://petstore.swagger.io/v2";
	
	//User module
	public static String post_url= base_url+"/user";
	public static String get_url= base_url+"/user/{username}";
	public static String update_url= base_url+"/user/{username}";
	public static String delete_url= base_url+"/user/{username}";
	
	//Store module
		//create store module URL's
	
	//Pet module
	//create pet module URL's

}
