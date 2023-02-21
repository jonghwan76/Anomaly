package com.realblox.dimetime.util;

import com.realblox.dimetime.util.exception.FileDownLoadException;
import com.realblox.dimetime.util.exception.FileException;
import com.realblox.dimetime.util.exception.FileNotAllowException;
import com.realblox.dimetime.util.exception.FileUpLoadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class FileUtil {
	Logger log = LoggerFactory.getLogger( FileUtil.class );

//	@Value("${path.buildingPath}")
//	private String rootPath = "";

	//불가 파일 확장자(추가 대상이 필요한 경우 추가)
	private final String[] arlUnsafeExtension= {"exe","ad", "adprototype", "asa", "asax", "ascx", "ashx", "asmx", "asp", "aspx", "axd",
		"browser", "cd", "cdx", "cer", "compiled", "config", "cs", "csproj", "dd", "exclued", "idc", "inc", "java", "jsl", "ldb", "ldd", "lddprototype",
		"ldf", "licx", "master", "mdb", "mdf", "msgx", "refresh", "rem", "resources", "resx", "sd", "sdm", "sdmdocument", "shtm", "shtml",
		"sitemap", "skin", "soap", "stm", "svc", "vb", "vbproj", "vjsproj", "vsdisco", "webinfo","dll" };

	private static final int BUFF_SIZE = 2048;

	/**
	 * 특정디렉토리로 파일명 그대로 업로드
	 * @param multipartFileList
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> uploadFiles(List<MultipartFile> multipartFileList, String rootPath, String subFolder) throws Exception
	{
		List<Map<String,Object>> fileMapList = new ArrayList<Map<String,Object>>();

		for(MultipartFile multipartFile : multipartFileList){
			if(multipartFile != null && !multipartFile.isEmpty()){
				isValidFile(multipartFile);

				long size = multipartFile.getSize();

				if (multipartFile.getSize() > 0) {

//					String uploadSubPath = new SimpleDateFormat("yyyyMM\\dd\\", Locale.getDefault()).format(new Date());
					String stordFilePath = rootPath + subFolder;
					String orginalFileName = multipartFile.getOriginalFilename();
//					String fileExt = StringUtils.getFilenameExtension(orginalFileName.toLowerCase());

//					newName = getFileSaveName(fileExt);

					try {
						writeFile(multipartFile, orginalFileName, stordFilePath);
					} catch(Exception e) {
						log.error("FileUtil.uploadFiles() Error : ", e);

						throw new Exception(e.getMessage());
					}

					Map<String,Object> fileMap = new HashMap<String,Object>();
					fileMap.put("stordFilePath", stordFilePath);
					fileMap.put("original_file_name", orginalFileName);
					fileMap.put("file_size", Long.toString(size));
					fileMap.put("fullFilePath", stordFilePath + orginalFileName);

					fileMapList.add(fileMap);
				}
			}
		}

		return fileMapList;
	}

	/**
	 * 오늘날짜 디렉토리로 새로운 파일명을 부여해서 업로드
	 * @param multipartFileList
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> uploadNewFiles(List<MultipartFile> multipartFileList, String rootPath) throws Exception
	{
		List<Map<String,Object>> fileMapList = new ArrayList<Map<String,Object>>();

		for(MultipartFile multipartFile : multipartFileList){
			if(multipartFile != null && !multipartFile.isEmpty()){
				isValidFile(multipartFile);

				long size = multipartFile.getSize();

				if (multipartFile.getSize() > 0) {

					String newName = "";
					String uploadSubPath = new SimpleDateFormat("yyyyMM\\dd\\", Locale.getDefault()).format(new Date());
					String stordFilePath = rootPath + uploadSubPath;
					String orginalFileName = multipartFile.getOriginalFilename();
					String fileExt = StringUtils.getFilenameExtension(orginalFileName.toLowerCase());

					newName = getFileSaveName(fileExt);

					try {
						writeFile(multipartFile, newName, stordFilePath);
					} catch(Exception e) {
						log.error("FileUtil.uploadFiles() Error : ", e);

						throw new Exception(e.getMessage());
					}

					Map<String,Object> fileMap = new HashMap<String,Object>();
					fileMap.put("stordFilePath", stordFilePath);
					fileMap.put("upload_file_name", newName);
					fileMap.put("original_file_name", orginalFileName);
					//fileMap.put("original_file_name", new String(orginalFileName.getBytes("8859_1"),"UTF-8"));//한글 깨짐 수정
					fileMap.put("upload_sub_path", uploadSubPath);
					fileMap.put("file_extension", fileExt);
					fileMap.put("file_size", Long.toString(size));

					fileMapList.add(fileMap);
				}
			}
		}

		return fileMapList;
	}
	/**
	 * 파일 다운로드
	 * ex)
	 * FileUtil fileUtil = new FileUtil();
	 * fileUtil.downFile(response,request,"/uploadRootPath/","uploadSubPath/","uploadFileName","downloadFilename");
	 * 파일 삭제 시 오류 시 예외에 따른 별도의 메세지를 받아 처리한다.
	 * ex)e.getMessage();
	 * @param response
	 * @param request
	 * @param downloadFilename : 다운로드 시 사용될 파일 이름
	 * @throws FileException
	 */
	public void downFile(HttpServletResponse response, HttpServletRequest request, String filepath, String downloadFilename) throws FileException {

		if (log.isDebugEnabled()) {
			log.debug("fileUtil.downFile()");
		}

		filepath = filepath.replaceAll("\r", "").replaceAll("\n", "");

		File file = new File(filePathBlackList(filepath));


		if (file.exists()) {
			try {
				String browser=getBrowser(request);

				if ("Opera".equals(browser)){
				    response.setContentType("application/octet-stream;charset=UTF-8");
				}else{
					response.setContentType("application/x-msdownload");
				}

				response.setContentLength((int)file.length());
				response.setHeader(	"Content-Disposition",	"attachment;filename=\""+ getDownFileNames(browser,downloadFilename) + "\";");
				response.setHeader("Content-Transfer-Encoding", "binary");

				BufferedInputStream in = null;
				BufferedOutputStream out = null;

				if (log.isDebugEnabled()) {
					log.debug("fileUtil.downFile Info : "+ filepath + downloadFilename);
				}

				try {
					in = new BufferedInputStream(new FileInputStream(file));
					out = new BufferedOutputStream(response.getOutputStream());

					FileCopyUtils.copy(in, out);
					out.flush();
				} catch (Exception ex) {
					log.debug("IGNORED : "+ ex.toString());
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (Exception ignore) {
							log.debug("fileUtil.downFile() : "+ ignore.toString());
						}
					}
					if (out != null) {
						try {
							out.close();
						} catch (Exception ignore) {
							log.debug("fileUtil.downFile() : "+ ignore.toString());
						}
					}
				}
			}

			catch (Exception e) {
				log.debug("fileUtil.downFile() Error : "+ e.toString());
				throw new FileDownLoadException(e);
			}
		} else {
			try{
				fileDownErrMSGReponse(request, response, "fileUtil.notFoundFile");
			}catch(IOException e){
				log.debug("fileUtil.downFile() Error : "+ e.toString());

				throw new FileDownLoadException(e);
			}
		}
	}

	/**
	 * 내부 함수
	 * 파일 이름 甥3성
	 *
	 * @param fileExt
	 * @return
	 * String : ex)년4 월2 일2 시2 분2 초2 밀리3 랜덤4.fileExt -> 201302020922001547.exe
	 */
	private String getFileSaveName(String fileExt) {
		if (log.isDebugEnabled()) {
			log.debug("FileUtil.getFileSaveName()");
		}

		Random random = new Random();
		Calendar calender = Calendar.getInstance();
		String uploadFilename = String.format("%04d%02d%02d%02d%02d%02d%03d%04d.%s",
																		calender.get(Calendar.YEAR),
																		(calender.get(Calendar.MONTH) + 1),
																		calender.get(Calendar.DAY_OF_MONTH),
																		calender.get(Calendar.HOUR_OF_DAY),
																		calender.get(Calendar.MINUTE),
																		calender.get(Calendar.SECOND),
																		calender.get(Calendar.MILLISECOND),
																		random.nextInt(1000),
																		fileExt);
		return uploadFilename;
	}

	/**
	 * 파일 업로드 시 보안처리 내부함수
	 * @param multipartFile
	 * @throws FileNotAllowException
	 */
	private void isValidFile(MultipartFile multipartFile) throws Exception {
		//확장자 체크
		String orginalFileName = multipartFile.getOriginalFilename();
		String fileExt = StringUtils.getFilenameExtension(orginalFileName.toLowerCase());

		for (String unsafeExtension : arlUnsafeExtension) {
			if(fileExt.equalsIgnoreCase(unsafeExtension))
				throw new Exception("업로드가 허가되지 않는 파일 유형 입니다.");
		}
	}

	/**
	 * 내부 함수
	 * 파일을 실제 물리적인 경로에 생성한다.
	 * @param file : MultipartFile
	 * @param newName : 파일 이름
	 * @param stordFilePath : 파일 저장 경로
	 * @throws FileUpLoadException
	 * @throws Exception
	 */
	private void writeFile(MultipartFile file, String newName, String stordFilePath) throws  Exception{
		if (log.isDebugEnabled()) {
			log.debug("FileUtil.writeFile()");
		}
		InputStream stream = null;
		OutputStream bos = null;

		try {

			File cFile = new File(filePathBlackList(stordFilePath));

			if (!cFile.isDirectory()){
				if(!cFile.mkdirs()){//하위 디렉토리 까지 생성
					log.error("fileUtil.writeFile() : directory permison error: 디랙토리 생성 권한을 확인하세요 : "+stordFilePath);
					return ;
				};
			}

			bos = new FileOutputStream(	filePathBlackList(stordFilePath	+ File.separator + newName));

			int bytesRead = 0;
			byte[] buffer = new byte[BUFF_SIZE];

			stream = file.getInputStream();

			while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
		}
		catch (Exception e) {
			log.error("FileUtil.writeFile() Error : ", e);

			throw new Exception("fileUtil.errFileUpload");
		}
		finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e) {
					log.error("FileUtil.writeFile() Error : ", e);

					throw new Exception("fileUtil.errFileUpload");
				}
			}
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception e) {
					log.error("FileUtil.writeFile() Error : ", e);

					throw new Exception("fileUtil.errFileUpload");
				}
			}
		}
	}
	/**
	 * 파일 보안처리 내부함수
	 * 강제 폴더 이동 삭제
	 * @param value
	 * @return String : 강제 폴더 이동된 문자열 삭제
	 */
	private String filePathBlackList(String value) {
		if (log.isDebugEnabled()) {
			log.debug("FileUtil.filePathBlackList()");
		}

		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\

		return returnValue;
	}

	/**
	 * 내부 함수
	 * 브라우저 정보 확인 함수
	 * @param request
	 * @return String
	 */

	private String getBrowser(HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("FileUtil.getBrowser()");
		}

		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		} else if (header.indexOf("Safari") > -1) {
			return "Safari";
		}
		return "Firefox";
	}

	/**
	 * 내부함수
	 * 다운로드 될 파일이름을 브라우저 별로 인코딩 하여 리턴 함
	 * @param browser
	 * @param fileName : 다운로드 시 보여질 파일이름
	 * @return String : 인코딩된 파일이름
	 */
	private String getDownFileNames(String browser,String fileName)
	{
		if (log.isDebugEnabled()) {
			log.debug("FileUtil.getDownFileNames()");
		}


		if ( fileName == null || fileName.equals("") ) {
			fileName = "UnKnownFileName";
		}

		String resultName = "";

		try{
			// Explorer
			if ( browser.indexOf("MSIE") != -1 ) {
				resultName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
			}
			// Opera
			else if ( browser.indexOf("Opera") != -1 ) {
				resultName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
			}
			// Chrome
			else if ( browser.indexOf("Chrome") != -1 ) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < fileName.length(); i++) {
					char c = fileName.charAt(i);
					if (c > '~') {
						sb.append(URLEncoder.encode("" + c, "UTF-8"));
					} else {
						sb.append(c);
					}
				}
				resultName = sb.toString();
			}
			// Safari
			else if ( browser.indexOf("Safari") != -1 ) {
				resultName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
			}
			// FireFox
			else if ( browser.indexOf("Firefox") != -1 ) {
				resultName = new String( fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
			// Other
			else{
				resultName = new String( fileName.getBytes("EUC-KR"), "ISO-8859-1");
			}
		} catch (Exception ex) {
			resultName = fileName;
		}
		return resultName;
	}

	/**
	 * 내부 함수
	 * 웹 상의 파일 다운로드 시 에러 메세지 처리
	 * @param request
	 * @param response
	 * @param errMSG : 에러 메세지
	 * @throws IOException
	 */
	private void fileDownErrMSGReponse(HttpServletRequest request, HttpServletResponse response,
                                       String errMSG) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("FileUtil.fileDownErrMSGReponse()");
		}
		//response.setContentType("application/x-msdownload");
		response.setContentType("text/html");
		PrintWriter printwriter = response.getWriter();
		printwriter.println("<script type='text/javascript'>alert('"+errMSG+"');history.go(-1);</script>");
		printwriter.flush();
		printwriter.close();
	}
}