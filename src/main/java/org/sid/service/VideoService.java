package org.sid.service;

import java.nio.file.Path;

import org.springframework.stereotype.Service;

import object.VideoResponse;

public interface VideoService {
	
	public VideoResponse getVideoRange(Path path, long from);

}
