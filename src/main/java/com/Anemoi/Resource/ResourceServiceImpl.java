package com.Anemoi.Resource;

import java.io.InputStream;

import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;

import jakarta.inject.Singleton;

@Singleton
public class ResourceServiceImpl implements ResourceSerice {

	@Override
	public Media getAnalystDetailsFile(String fileType) {
		try {
			Media media = new Media();
			if (fileType.contentEquals("Master Database")) {

				String analystDetailsfile = ReadPropertiesFile.readRequestProperty("media.resource.analyst.file");
				System.out.println(analystDetailsfile);
				InputStream is = getClass().getClassLoader().getResourceAsStream(analystDetailsfile);
				media.setMediaName(analystDetailsfile);
				media.setMediaInputStream(is);
				return media;

			} else if (fileType.contentEquals("Details")) {
				String analystDetailsfile = ReadPropertiesFile.readRequestProperty("media.resource.Details.file");
				System.out.println(analystDetailsfile);
				InputStream is = getClass().getClassLoader().getResourceAsStream(analystDetailsfile);
				media.setMediaName(analystDetailsfile);
				media.setMediaInputStream(is);
				return media;
			}

			else if (fileType.contentEquals("Line Item Nomenclature")) {

				String analystDetailsfile = ReadPropertiesFile.readRequestProperty("media.resource.Nomenclature.file");
				System.out.println(analystDetailsfile);
				InputStream is = getClass().getClassLoader().getResourceAsStream(analystDetailsfile);
				media.setMediaName(analystDetailsfile);
				media.setMediaInputStream(is);
				return media;
			} else if (fileType.contentEquals("Client Line Item Nomenclature")) {

				String analystDetailsfile = ReadPropertiesFile
						.readRequestProperty("media.resource.ClientLineItemNomenclature.file");
				System.out.println(analystDetailsfile);
				InputStream is = getClass().getClassLoader().getResourceAsStream(analystDetailsfile);
				media.setMediaName(analystDetailsfile);
				media.setMediaInputStream(is);
				return media;
			}

			else if (fileType.contentEquals("Shareholder Data")) {

				String analystDetailsfile = ReadPropertiesFile
						.readRequestProperty("media.resource.Shareholderdata.file");
				System.out.println(analystDetailsfile);
				InputStream is = getClass().getClassLoader().getResourceAsStream(analystDetailsfile);
				media.setMediaName(analystDetailsfile);
				media.setMediaInputStream(is);
				return media;
			} else if (fileType.contentEquals("Meeting Data")) {

				String analystDetailsfile = ReadPropertiesFile.readRequestProperty("media.resource.meetingdata.file");
				System.out.println(analystDetailsfile);
				InputStream is = getClass().getClassLoader().getResourceAsStream(analystDetailsfile);
				System.out.println("is" + is);
				media.setMediaName(analystDetailsfile);
				media.setMediaInputStream(is);
				return media;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
