package object;

public class VideoResponse {
	
	public byte[] video;
	public long readBytes;
	public long videoSize;
	
	
	public VideoResponse(int range) {
		video = new byte[range];
	}
	
}
