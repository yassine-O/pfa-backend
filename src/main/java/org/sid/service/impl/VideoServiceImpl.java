package org.sid.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;

import org.sid.service.VideoService;
import org.springframework.stereotype.Service;

import object.VideoResponse;

@Service
public class VideoServiceImpl implements VideoService {

	@Override
	public VideoResponse getVideoRange(Path path, long from) {
		int range = 1*1024*1024;
		VideoResponse videoResponse = new VideoResponse(range);
		File file = new File(path.toString());
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			fis.skip(from);
			videoResponse.readBytes = fis.read(videoResponse.video);
			videoResponse.videoSize = file.length();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoResponse;
	}

}
