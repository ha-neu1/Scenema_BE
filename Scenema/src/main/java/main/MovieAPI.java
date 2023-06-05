package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 
import org.json.JSONArray;
import org.json.JSONObject;
 
public class MovieAPI {
    // ��� ���� - ��û(Request) ��û ����
    private final String REQUEST_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
    private final String AUTH_KEY = "12d3990ca0efc626624eb0ddf4269a36";
 
    // ���� ����(�Ϸ� ��)
    private final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyyMMdd");
 
    // Map -> QueryString
    public String makeQueryString(Map<String, String> paramMap) {
        final StringBuilder sb = new StringBuilder();
 
        paramMap.entrySet().forEach(( entry )->{
            if( sb.length() > 0 ) {
                sb.append('&');
            }
            sb.append(entry.getKey()).append('=').append(entry.getValue());
        });
        return sb.toString();
    }
 
    // API��û
    public String requestAPI() {
        // �������� - �Ϸ��� ��¥
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);
        String result = "";
 
        // ���� ���� - ��û(Request) �������̽� Map
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key"          , AUTH_KEY);                        // �߱޹��� ����Ű
        paramMap.put("targetDt"     , DATE_FMT.format(cal.getTime()));  // ��ȸ�ϰ��� �ϴ� ��¥
        paramMap.put("itemPerPage"  , "10");                            // ��� ROW �� ����( �ִ� 10�� )
 
        try {
            // Request URL ���� ��ü ����
            URL requestURL = new URL(REQUEST_URL+"?"+makeQueryString(paramMap));
            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
 
            // GET ������� ��û
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
 
            // ����(Response) ���� �ۼ�
            //   - Stream -> JSONObject
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String readline = null;
            StringBuffer response = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                response.append(readline);
            }
 
            // JSON ��ü��  ��ȯ
            JSONObject responseBody = new JSONObject(response.toString());
 
            // ������ ����
            JSONObject boxOfficeResult = responseBody.getJSONObject("boxOfficeResult");
 
            // �ڽ����ǽ� ���� ���
            String boxofficeType = boxOfficeResult.getString("boxofficeType");
 
            // �ڽ����ǽ� ��� ���
            JSONArray dailyBoxOfficeList = boxOfficeResult.getJSONArray("dailyBoxOfficeList");
            Iterator<Object> iter = dailyBoxOfficeList.iterator();
            while(iter.hasNext()) {
                JSONObject boxOffice = (JSONObject) iter.next();
                result += boxOffice.get("movieNm")+"|";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public static void main(String[] args) {
    	MovieAPI api = new MovieAPI();
    	System.out.println(api.requestAPI());
	}
}
