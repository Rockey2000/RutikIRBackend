package com.Anemoi.InvestorRelation.ShareHolderContact;

import java.sql.SQLException;
import java.util.List;

import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/investor/shareholderContact")
public class ShareHolderContactController {

	@Inject
	private ShareHolderContactService service;

	@Post("/add")
	public HttpResponse<ShareHolderContactEntity> addShareholderContactDetails(
			@Body ShareHolderContactEntity shareholdercontactEntity) throws ShareHolderContactControllerException {
		try {

			ShareHolderContactEntity newshareholdercontact = this.service
					.createShareHolderContact(shareholdercontactEntity);
			return HttpResponse.status(HttpStatus.OK).body(newshareholdercontact);

		}

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			throw new ShareHolderContactControllerException(ReadPropertiesFile.readResponseProperty("403"), e, 406,
					e.getMessage());

		}

	}

	@Get("/{contactid}")
	public HttpResponse<ShareHolderContactEntity> getDataById(@PathVariable("contactid") String contactid)
			throws ShareHolderContactControllerException {
		try {
			ShareHolderContactEntity shareholdercontactEntity = this.service.getShareHolderContactById(contactid);
			return HttpResponse.status(HttpStatus.OK).body(shareholdercontactEntity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShareHolderContactControllerException(ReadPropertiesFile.readResponseProperty("101"), e, 400,
					e.getMessage());
		}

	}

	@Get("/list")
	public List<ShareHolderContactEntity> getDetails() throws SQLException, ShareHolderContactControllerException {
		try {
			List<ShareHolderContactEntity> shareholdercontact = this.service.getAllShareholderContactDetails();
			return shareholdercontact;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ShareHolderContactControllerException(ReadPropertiesFile.readResponseProperty("101"), e, 400,
					e.getMessage());
		}

	}

	@Patch("/{contactid}")
	public HttpResponse<ShareHolderContactEntity> updateShareholderContact(
			@Body ShareHolderContactEntity shareholdercontactEntity, @PathVariable("contactid") String contactid)
			throws ShareHolderContactControllerException {
		try {
			ShareHolderContactEntity updatedcashflow = this.service.updateShareHolderContact(shareholdercontactEntity,
					contactid);
			return HttpResponse.status(HttpStatus.OK).body(updatedcashflow);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ShareHolderContactControllerException(ReadPropertiesFile.readResponseProperty("403"), e, 406,
					e.getMessage());
		}

	}

	@Delete("/{contactid}")
	public HttpResponse<ShareHolderContactEntity> deleteShareHolderContact(@PathVariable("contactid") String contactid)
			throws ShareHolderContactControllerException {
		try {
			ShareHolderContactEntity response = this.service.deleteShareHolderContact(contactid);
			return HttpResponse.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ShareHolderContactControllerException(ReadPropertiesFile.readResponseProperty("101"), e, 400,
					e.getMessage());
		}

	}

}
