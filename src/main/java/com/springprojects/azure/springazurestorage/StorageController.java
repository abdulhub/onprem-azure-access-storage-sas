package com.springprojects.azure.springazurestorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.azure.storage.StorageCredentials;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.springprojects.azure.httproxyauth.ProxyAuthenticator;

@RestController
public class StorageController {

	@Value("${storage.sas.url}")
	private URI baseuri;

	@Value("${storage.download}")
	private String downloadPath;

	private CloudBlobClient blobClient;
	private CloudBlockBlob sasBlob;

	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();

	@GetMapping(path = "/{container}/{filename}/{sas}")
	public void downloadBlobWUsingSAS(@PathVariable("container") String container,
			@PathVariable("filename") String fileName, @PathVariable("sas") String sas) throws Exception {

		downloadBlob(baseuri, sas, container, fileName);
	}

	private void downloadBlob(URI baseuri, String containerSAS, String containerName, String blobName)
			throws URISyntaxException, StorageException, IOException {
		// TODO Auto-generated method stub

		CloudBlobClient blobClient = new CloudBlobClient(baseuri);

		String uriString = blobClient.getEndpoint().toString() + "/" + containerName + "/" + blobName + "?"
				+ containerSAS;
		URI uri = new URI(uriString);

		CloudBlockBlob sasBlob = new CloudBlockBlob(uri);

		File downloadfile = new File(this.downloadPath + "/" + blobName);
		FileOutputStream outPutStream = new FileOutputStream(downloadfile);
		sasBlob.downloadToFile(this.downloadPath + "/" + blobName);

	}

	@GetMapping("/test")
	public String doTest() {
		// http://localhost:9111/algofiles/mingw-get-setup.exe/sv=2018-03-28&ss=bfqt&srt=sco&sp=rl&st=2019-08-27T10%3A08%3A00Z&se=2019-08-28T10%3A08%3A00Z&sig=PxRfgfrLRq8w6hsTvnDQviRWLMkUfLsXUmr1QJGmsB0%3D
		// String uriString =
		// SharedAccessSignature=sv=2018-03-28&ss=bfqt&srt=sco&sp=rl&st=2019-08-27T10%3A08%3A00Z&se=2019-08-28T10%3A08%3A00Z&sig=PxRfgfrLRq8w6hsTvnDQviRWLMkUfLsXUmr1QJGmsB0%3D;BlobEndpoint=https://devstaccount.blob.core.windows.net/;FileEndpoint=https://devstaccount.file.core.windows.net/;QueueEndpoint=https://devstaccount.queue.core.windows.net/;TableEndpoint=https://devstaccount.table.core.windows.net/"
		/*
		 * String uriString = blobClient.getEndpoint().toString() + "/" + containerName
		 * + "/" + blobName +
		 * "?sv=2018-03-28&ss=bfqt&srt=sco&sp=rl&st=2019-08-27T10%3A08%3A00Z&se=2019-08-28T10%3A08%3A00Z&sig=PxRfgfrLRq8w6hsTvnDQviRWLMkUfLsXUmr1QJGmsB0%3D";
		 */
		return "Hello";
	}

}
