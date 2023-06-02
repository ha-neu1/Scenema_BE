package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.MovieDTO;
import main.MovieDBMain;
import service.MovieDBService;

@Controller
public class DetailController {
	@Autowired
	MovieDBService service;
	
	@RequestMapping("/detailpage")
	public ModelAndView detailPage(int movieid) {
		MovieDTO dto = service.getMovieFromID(movieid);
		
		//포스터 및 스틸컷 하나씩 별도 분리
		String posters [] = dto.getPosterurl().split("\\|");
		String stillcuts [] = dto.getStillcuturls().split("\\|");
		
		//View에 보일 날짜 형식 바꾸기
		String year = dto.getReleaseDate().substring(0, 4);
		String month = dto.getReleaseDate().substring(4, 6);
		String day = dto.getReleaseDate().substring(6, 8);
		dto.setReleaseDate(year+"."+month+"."+day);
		
		//story 엔터삽입(추후구현)
//		String story = dto.getStory();
//		String newstory = story.replaceAll("!","!<br>");
//		newstory = newstory.replaceAll("\\.","\\.<br>");
//		System.out.println(newstory);
//		dto.setStory(story);
		
		//Video url
		String newVideoUrls = videourl(dto.getVideourl());
		String newVideos [] = newVideoUrls.split("\\|");
		
		for(int i=0; i<newVideos.length ; i ++) {
			System.out.println(newVideos[i]);
		}
		System.out.println(newVideos.length);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("movie",dto);
		mv.addObject("posters", posters);
		mv.addObject("stillcuts", stillcuts);
		mv.addObject("videos", newVideos);
//		mv.setViewName("test");
		mv.setViewName("DetailPage");
		return mv;
	}
	
	//동영상 url 변환용 메서드 - 추후 db수정
    public static String videourl(String videourl) {
    	String pageContents = "";
    	StringBuilder contents = new StringBuilder();

    	if(!videourl.equals("")) {
    		String urlPath = videourl;
    		try{
    			URL url = new URL(urlPath);
    			URLConnection con = (URLConnection)url.openConnection();
    			InputStreamReader reader = new InputStreamReader (con.getInputStream(), "utf-8");
    			
    			BufferedReader buff = new BufferedReader(reader);
    			
    			while((pageContents = buff.readLine())!=null){
    				if(pageContents.contains("fcnPlay")&&pageContents.contains("\'")) {
    					String link = pageContents.strip();
    					link = link.substring(link.indexOf("\'")+1, link.lastIndexOf("\'"));
    					contents.append("https://www.kmdb.or.kr/trailer/play/"+link+"|");
    				}
    			}
//    			System.out.println(contents);
    			buff.close();
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}
        return contents.toString();
    }
}
