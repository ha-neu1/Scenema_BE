package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dto.MovieCommentDTO;
import dto.MovieDTO;
import service.MovieCommentService;
import service.MovieDBService;

@Controller
public class DetailController {
	@Autowired
	MovieDBService service;
	@Autowired
	MovieCommentService service_c;
	
	@RequestMapping("/detailpage")
	public ModelAndView detailPage(int movieid,
			@RequestParam(value="page", required=false, defaultValue="1" ) int page) {
		MovieDTO dto = service.getMovieFromID(movieid);
		
		//포스터 및 스틸컷 하나씩 별도 분리
		String posters [] = dto.getPosterurl().split("\\|");
		String stillcuts [] = dto.getStillcuturls().split("\\|");
		
		//View에 보일 날짜 형식 바꾸기
		String year = dto.getReleaseDate().substring(0, 4);
		String month = dto.getReleaseDate().substring(4, 6);
		String day = dto.getReleaseDate().substring(6, 8);
		dto.setReleaseDate(year+"."+month+"."+day);
		
		//Video url
		String newVideoUrls = videourl(dto.getVideourl());
		String newVideos [] = newVideoUrls.split("\\|");
		
		//영화 평점
		double movieScore = service_c.getMovieScore(movieid);
		movieScore = Double.parseDouble(String.format("%.1f",movieScore));
		
		//영화 평점댓글
		int commentsCount = service_c.getCommentsCount(movieid); //전체 영화 댓글 개수
		
		HashMap<String, Integer> cmtmap = new HashMap<String, Integer>();
		cmtmap.put("movieid", movieid);
		cmtmap.put("page",(page-1)*10);
		cmtmap.put("limit",10);
		
		List<MovieCommentDTO> comments = service_c.getPagingComments(cmtmap); // 댓글 리스트(page=1)
		
		
		//댓글 시간변경
		for(MovieCommentDTO cmts : comments) {
			String str = cmts.getCreateAt();
			str = str.substring(0,str.lastIndexOf(" "));
			cmts.setCreateAt(str);
		}
		
		//ModelAndView 객체
		ModelAndView mv = new ModelAndView();
		mv.addObject("movie",dto);
		mv.addObject("posters", posters);
		mv.addObject("stillcuts", stillcuts);
		mv.addObject("videos", newVideos);
		mv.addObject("comments", comments);
		mv.addObject("commentsCount", commentsCount);
		mv.addObject("movieScore", movieScore);
		
//		mv.setViewName("test");
		mv.setViewName("DetailPage");
		return mv;
	}
	
	//평점댓글 작성
	@RequestMapping(value="/commentinsert", produces = {"application/json;charset=utf-8"})
	public @ResponseBody List<MovieCommentDTO> detailComment(int movieid, String userid, int score, String contents) {
		MovieCommentDTO dto = new MovieCommentDTO();
		dto.setMovieid(movieid);
		dto.setContents(contents);
		dto.setUserid(userid);
		dto.setScore(score);
		
		service_c.insertMovieComment(dto);
		
		List<MovieCommentDTO> comments_new = service_c.getMovieComments(movieid);
		
		return comments_new;
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
