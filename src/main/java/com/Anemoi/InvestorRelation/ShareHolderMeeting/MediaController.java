package com.Anemoi.InvestorRelation.ShareHolderMeeting;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64InputStream;

import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;
import com.Anemoi.InvestorRelation.ShareHolderContact.ShareHolderContactControllerException;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;

import jakarta.inject.Inject;

@Controller("/investor/shareholdermeeting")
public class MediaController {

	@Inject
	private MediaServiceInterface serviceInterface;

	@Post(uri = "/uploadmedia", consumes = { MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA })
	public MutableHttpResponse<ShareHolderMeetingEntity> postMediaFile(CompletedFileUpload file) throws ShareHolderMeetingControllerExcetion {
		
			String key = UUID.randomUUID().toString();
			try {
			this.serviceInterface.upload(key, file);
			ShareHolderMeetingEntity e=new ShareHolderMeetingEntity();
			e.setMediakey(key);
			return HttpResponse.status(HttpStatus.OK).body(e);

		} catch (Exception e) {

			throw new ShareHolderMeetingControllerExcetion(ReadPropertiesFile.readResponseProperty("101"), e, 400,
					e.getMessage());

		}

	}

	@Get( uri = "playAudioVideoFile/{mediakey}",produces = {MediaType.APPLICATION_OCTET_STREAM})
	public MutableHttpResponse<InputStream> getAudioVedioFileData(@PathVariable("mediakey") String mediakey)
			throws ShareHolderMeetingControllerExcetion {
		try {

            File file =this. serviceInterface.getMediaFileByKey(mediakey);
            if (file.exists()) {
                 String contentType = "video/mp4"; // Set appropriate content type for video or audio files
                 InputStream inputStream = new FileInputStream(file);
                 return HttpResponse.ok(inputStream)
                         .contentType(contentType)
                         .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"");
             }
            else {
                 return HttpResponse.notFound();
             }

        } catch (Exception e) {

            throw new ShareHolderMeetingControllerExcetion(ReadPropertiesFile.readResponseProperty("101"), e, 400,
                    e.getMessage());
        }

	}
	
	@Get("downloadAudioVideoFile/{mediakey}")
	public MutableHttpResponse<byte[]> downloadAudioVedioFileData(@PathVariable("mediakey") String mediakey)
			throws ShareHolderMeetingControllerExcetion {
		try {
			File file =this. serviceInterface.getMediaFileByKey(mediakey);
		System.out.println("file"+file);
			byte[] fileData = Files.readAllBytes(file.toPath());
			if (fileData != null) {
				return HttpResponse.ok(fileData)
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
	                    .contentType(MediaType.APPLICATION_OCTET_STREAM);
			} 
			else {
				return HttpResponse.notFound();
			}
		} catch (Exception e) {
			throw new ShareHolderMeetingControllerExcetion(ReadPropertiesFile.readResponseProperty("101"), e, 400,
					e.getMessage());
		}

	}
}
