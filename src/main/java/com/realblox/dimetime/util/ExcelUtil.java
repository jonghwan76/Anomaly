package com.realblox.dimetime.util;

import com.realblox.dimetime.model.RiskVO;
import com.realblox.dimetime.service.PatternService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 엑셀 읽기 유틸리티
 */
@Slf4j
public class ExcelUtil {
	@Autowired
	PatternService patternService;

	public List<RiskVO> readCsv(String path, String today)  {
		log.info("========================= readCsv Start =========================");

		List<RiskVO> list = new ArrayList<>();

		File csv = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csv));
			Charset.forName("UTF-8");
			String line = "";

			while((line=br.readLine()) != null) {
//				log.info("line data:" + line);
				String[] token = line.split(",");
				RiskVO riskVO = new RiskVO();
				riskVO.setUser_id(token[0]);
				riskVO.setGps_variation(token[1]);
				riskVO.setTime_amount(token[2]);
				riskVO.setStat_dt(today);
				list.add(riskVO);
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
		log.info("========================= readCsv End =========================");
		return list;

	}
	
}
