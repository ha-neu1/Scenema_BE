package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import dao.MovieDAO;
import dto.MovieDTO;
 
public class MovieDBMain {
    // 상수 설정 - 요청(Request) 요청 변수
    private final String REQUEST_URL = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp";
    private final String AUTH_KEY = "N17IX12019AYWQVW2I2K";
 
    // Map -> QueryString
    public String makeQueryString(Map<String, String> paramMap) {
        final StringBuilder sb = new StringBuilder();
 
        paramMap.entrySet().forEach(new Consumer<Entry<String, String>>() {
			public void accept(Entry<String, String> entry) {
			    if( sb.length() > 0 ) {
			        sb.append('&');
			    }
			    sb.append(entry.getKey()).append('=').append(entry.getValue());
			}
		});
        return sb.toString();
    }
 
    // API요청 - 20230101~0531
    public ArrayList<MovieDTO> requestAPI() {
    	ArrayList<MovieDTO> dtoarray = new ArrayList<MovieDTO>();
    	
        // 변수 설정 - 요청(Request) 인터페이스 Map
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("ServiceKey", AUTH_KEY);    // 발급받은 인증키
        paramMap.put("collection", "kmdb_new2");    // 컬렉션
        paramMap.put("detail", "Y");    // 디테일
        paramMap.put("releaseDts", "20230101");  // 조회하고자 하는 날짜
        paramMap.put("releaseDte", "20230531");  // 조회하고자 하는 날짜
        paramMap.put("listCount", "300");        // 결과 ROW 의 개수( 최대 10개 )
 
        try {
            // Request URL 연결 객체 생성
            URL requestURL = new URL(REQUEST_URL+"?"+makeQueryString(paramMap));
            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
 
            // GET 방식으로 요청
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
 
            // 응답(Response) 구조 작성 - Stream -> JSONObject
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String readline = null;
            StringBuffer response = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                response.append(readline);
            }
 
            // JSON 객체로  변환
            JSONObject responseBody = new JSONObject(response.toString());
            
            // 데이터 추출
            JSONArray Datas = responseBody.getJSONArray("Data");
            JSONObject Datas2 = Datas.getJSONObject(0);
            JSONArray Results = Datas2.getJSONArray("Result");
            
            //System.out.println(Results);
            
            for(int i=0; i<Results.length(); i++) {
            	MovieDTO dto = new MovieDTO();
            	dto.setTitle(Results.getJSONObject(i).get("title").toString().strip());
            	dto.setTitleEng(Results.getJSONObject(i).get("titleEng").toString());
            	dto.setStory(Results.getJSONObject(i).getJSONObject("plots").getJSONArray("plot").getJSONObject(0).get("plotText").toString());
            	dto.setGenre(Results.getJSONObject(i).get("genre").toString());
            	dto.setRuntime(Integer.parseInt(Results.getJSONObject(i).get("runtime").toString()));
            	dto.setDirector(Results.getJSONObject(i).getJSONObject("directors").getJSONArray("director").getJSONObject(0).get("directorNm").toString());
            	dto.setReleaseDate(Results.getJSONObject(i).get("repRlsDate").toString());
            	dto.setRating(Results.getJSONObject(i).get("rating").toString());
            	dto.setPosterurl(Results.getJSONObject(i).get("posters").toString());
            	dto.setStillcuturls(Results.getJSONObject(i).get("stlls").toString());
            	dto.setVideourl(Results.getJSONObject(i).getJSONObject("vods").getJSONArray("vod").getJSONObject(0).get("vodUrl").toString());
            	
            	dtoarray.add(dto);
            }
            System.out.println(Results.getJSONObject(0).getJSONObject("vods").getJSONArray("vod").getJSONObject(0).get("vodUrl").toString());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return dtoarray;
    }
    
    // API요청(특정영화)
    public MovieDTO requestAPIone(String title) throws UnsupportedEncodingException {
    	MovieDTO dto = new MovieDTO();
    	
    	String encodeResult = URLEncoder.encode(title, "UTF-8");
    	
        // 변수 설정 - 요청(Request) 인터페이스 Map
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("ServiceKey", AUTH_KEY); // 발급받은 인증키
        paramMap.put("collection", "kmdb_new2"); // 컬렉션
        paramMap.put("detail", "Y");
        paramMap.put("title", encodeResult); //타이틀
 
        try {
            // Request URL 연결 객체 생성
            URL requestURL = new URL(REQUEST_URL+"?"+makeQueryString(paramMap));
            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
 
            // GET 방식으로 요청
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
 
            // 응답(Response) 구조 작성 - Stream -> JSONObject
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String readline = null;
            StringBuffer response = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                response.append(readline);
            }
 
            // JSON 객체로  변환
            JSONObject responseBody = new JSONObject(response.toString());
            
            // 데이터 추출
            JSONArray Datas = responseBody.getJSONArray("Data");
            JSONObject Datas2 = Datas.getJSONObject(0);
            JSONArray Results = Datas2.getJSONArray("Result");
            
            //System.out.println(Results);
            
            dto = new MovieDTO();
            dto.setTitle(Results.getJSONObject(0).get("titleEtc").toString().split(",")[1].strip());
            dto.setTitleEng(Results.getJSONObject(0).get("titleEng").toString());
            dto.setStory(Results.getJSONObject(0).getJSONObject("plots").getJSONArray("plot").getJSONObject(0).get("plotText").toString());
            dto.setGenre(Results.getJSONObject(0).get("genre").toString());
            dto.setRuntime(Integer.parseInt(Results.getJSONObject(0).get("runtime").toString()));
            dto.setDirector(Results.getJSONObject(0).getJSONObject("directors").getJSONArray("director").getJSONObject(0).get("directorNm").toString());
            dto.setReleaseDate(Results.getJSONObject(0).get("repRlsDate").toString());
            dto.setRating(Results.getJSONObject(0).get("rating").toString());
            dto.setPosterurl(Results.getJSONObject(0).get("posters").toString());
            dto.setStillcuturls(Results.getJSONObject(0).get("stlls").toString());
            dto.setVideourl(Results.getJSONObject(0).getJSONObject("vods").getJSONArray("vod").getJSONObject(0).get("vodUrl").toString());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return dto;
    }
 
    public static void main(String[] args) throws IOException {
    	SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(Resources.getResourceAsReader("common/mybatis-config-main.xml"));
		SqlSession session = factory.openSession(true); //auto commit true
    	
		MovieDAO dao = new MovieDAO();
		dao.setSession(session);
		
        // API 객체 생성
        MovieDBMain api = new MovieDBMain();
 
        // Database 생성
        ArrayList<MovieDTO> dtoarray = api.requestAPI();
        for(MovieDTO dto : dtoarray) {
        	dao.insertMovieDB(dto);
        }
        
        // 재개봉영화 추가
        MovieDTO newdto = api.requestAPIone("극장판포켓몬스터DP:아르세우스초극의시공으로");
        System.out.println(newdto.getTitle());
        dao.insertMovieDB(newdto);
        
        
    }
}
