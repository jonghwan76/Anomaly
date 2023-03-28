package com.realblox.dimetime.util;

import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 엑셀 읽기 유틸리티
 */
@Slf4j
public class ExcelUtil {
	public static List<HashMap> readCsv(String path)  {
		List<HashMap> list = new ArrayList<>();

		File csv = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csv));
			Charset.forName("UTF-8");
			String line = "";

			while((line=br.readLine()) != null) {
				String[] token = line.split(",");
				HashMap hashMap = new HashMap();
				hashMap.put("col1", token[0]);
				hashMap.put("col2", token[1]);
				hashMap.put("col3", token[2]);
				list.add(hashMap);
			}

		} catch (FileNotFoundException e) {
			log.error(e.toString());
		} catch (IOException e) {
			log.error(e.toString());
		} finally {
			try {
				if(br != null) {br.close();}
			} catch (IOException e) {
				log.error(e.toString());
			}
		}

		return list;
	}
	
}
