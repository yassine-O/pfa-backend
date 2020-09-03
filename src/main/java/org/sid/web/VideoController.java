package org.sid.web;

import org.sid.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import constantes.Chemain;
import object.VideoResponse;

@Controller
@RequestMapping("watch")
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	
	@RequestMapping("questions/{id}")
	public ResponseEntity<byte[]> streamQuestion(@RequestHeader(value = "Range", required = false) String range, @PathVariable long id) {
		long from = Long.valueOf(range.replace("bytes=", "").split("-")[0]);
		VideoResponse videoResponse = videoService.getVideoRange(Chemain.pathQuestion(id), from);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.valueOf("video/mp4"));
		httpHeaders.set("Accept-Ranges","bytes");
		httpHeaders.set("Content-Range", "bytes "+from+"-"+(videoResponse.readBytes+from+1)+"/"+videoResponse.videoSize+1);
		httpHeaders.setContentLength(videoResponse.readBytes); 
		ResponseEntity<byte[]> result = null;
		result = new ResponseEntity<byte[]>(videoResponse.video, httpHeaders, HttpStatus.PARTIAL_CONTENT);
		return result;
	}
	
}
