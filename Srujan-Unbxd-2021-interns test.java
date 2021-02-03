import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class test
{
	public static void main(String [] args)
	{
		//inline will store the JSON data streamed in string format
		String inline = "";
		inline.split(",") // splitting each comma separated value
		for(int i=0;i<String.size();i++)
		{
		try
		{
			//Sending url for each iteration
			URL url = new URL("https://search.unbxd.io/fb853e3332f2645fac9d71dc63e09ec1/demo-unbxd700181503576558/search?&q=*&rows=i&start=i");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if(responsecode != 200)
				throw new RuntimeException("HttpResponseCode: " +responsecode);
			else
			{
				//Scanner functionality will read the JSON data from the stream
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext())
				{
					inline+=sc.nextLine();
				}
				System.out.println(inline);
				sc.close();
			}
			JSONParser parse = new JSONParser();
			JSONObject jobj = (JSONObject)parse.parse(inline);
			JSONArray jsonarr_1 = (JSONArray) jobj.get("searchMetaData");
			for(int i=0;i<jsonarr_1.size();i++)
			{
				JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
				JSONArray jsonarr_2 = (JSONArray) jsonobj_1.get("products");;
				System.out.println("Elements under products array");
				for(int j=0;j<jsonarr_2.size();j++)
				{
					JSONObject jsonobj_2 = (JSONObject) jsonarr_2.get(j);
					System.out.println("\n");
				}
			}
			conn.disconnect();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}