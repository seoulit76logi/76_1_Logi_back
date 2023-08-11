package kr.co.seoulit.logistics.prodcsvc.production.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nexacro.java.xapi.data.PlatformData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import kr.co.seoulit.logistics.busisvc.logisales.to.ContractDetailInMpsAvailableTO;
import kr.co.seoulit.logistics.prodcsvc.production.service.ProductionService;
import kr.co.seoulit.logistics.prodcsvc.production.to.MpsTO;
import kr.co.seoulit.logistics.prodcsvc.production.to.SalesPlanInMpsAvailableTO;
import kr.co.seoulit.logistics.sys.util.DatasetBeanMapper;

@RestController
@RequestMapping("/production/*")
public class MpsController {

	@Autowired
	private ProductionService productionService;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	ModelMap map = null;

	private static Gson gson = new GsonBuilder().serializeNulls().create();

	// MpsRegister - MPS 조회, 안사용하는듯?
	@RequestMapping(value="/mps/list")
	public void searchMpsInfo(@RequestAttribute("reqData") PlatformData reqData,
            					@RequestAttribute("resData") PlatformData resData )throws Exception {

		String startDate = reqData.getVariable("startDate").getString();
		String endDate = reqData.getVariable("endDate").getString();
		String includeMrpApply = reqData.getVariable("includeMrpApply").getString();

		ArrayList<MpsTO> mpsTOList = productionService.getMpsList(startDate, endDate, includeMrpApply);
		datasetBeanMapper.beansToDataset(resData, mpsTOList, MpsTO.class);
	}

	// newMpsByContract - 검색
	@RequestMapping(value = "/mps/contractdetail-available")
	public void searchContractDetailListInMpsAvailable(@RequestAttribute("reqData") PlatformData reqData,
													   @RequestAttribute("resData") PlatformData resData) throws Exception {

		String searchCondition = reqData.getVariable("searchCondition").getString(); // 수주상세 검색조건 선택(수주일자, 납기일)
		String startDate = reqData.getVariable("startDate").getString(); // 시작일
		String endDate = reqData.getVariable("endDate").getString(); // 종료일

		// productionService를 사용하여 데이터 조회 및 가공
		ArrayList<ContractDetailInMpsAvailableTO> contractDetailInMpsAvailableList = productionService
				.getContractDetailListInMpsAvailable(searchCondition, startDate, endDate); // 시작일, 종료일 사이에 있는

		// 조회 결과를 PlatformData의 dataset으로 변환하여 resData에 설정
		datasetBeanMapper.beansToDataset(resData, contractDetailInMpsAvailableList, ContractDetailInMpsAvailableTO.class);
	}


	// 생산계획이 가능한지 조회 - 잘 등록되면 성공!
	@RequestMapping(value="/mps/salesplban-availale", method=RequestMethod.GET)
	public ModelMap searchSalesPlanListInMpsAvailable(HttpServletRequest request, HttpServletResponse response) {
		// url에 붙어 있는 파라미터로 넥사크로에서 넘어온 값
		String searchCondition = request.getParameter("searchCondition");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		map = new ModelMap();
		try {
			ArrayList<SalesPlanInMpsAvailableTO> salesPlanInMpsAvailableList =
					productionService.getSalesPlanListInMpsAvailable(searchCondition, startDate, endDate);

			// 조회 결과를 map에 저장, json형식으로 응답
			map.put("gridRowJson", salesPlanInMpsAvailableList);
			map.put("errorCode", 1);
			map.put("errorMsg", "성공");
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("errorCode", -1);
			map.put("errorMsg", e1.getMessage());
		}
		return map;
	}


	// newMpsByContract - 등록
	@RequestMapping(value="mps/contractdetail")
	public void convertContractDetailToMps(@RequestAttribute("reqData") PlatformData reqData,
            @RequestAttribute("resData") PlatformData resData) throws Exception {

		ArrayList<ContractDetailInMpsAvailableTO> contractDetailInMpsAvailableList
		= (ArrayList<ContractDetailInMpsAvailableTO>)datasetBeanMapper.datasetToBeans(reqData, ContractDetailInMpsAvailableTO.class);

		List<MpsTO> mpsNoList = productionService.convertContractDetailToMps(contractDetailInMpsAvailableList);
		datasetBeanMapper.beansToDataset(resData, mpsNoList, MpsTO.class);
	}


	@RequestMapping(value="/mps/salesplan", method=RequestMethod.PUT)
	public ModelMap convertSalesPlanToMps(HttpServletRequest request, HttpServletResponse response) {
		String batchList = request.getParameter("batchList");
		map = new ModelMap();
		try {
			ArrayList<SalesPlanInMpsAvailableTO> salesPlanInMpsAvailableList = gson.fromJson(batchList,
					new TypeToken<ArrayList<SalesPlanInMpsAvailableTO>>() {
					}.getType());
			HashMap<String, Object> resultMap = productionService.convertSalesPlanToMps(salesPlanInMpsAvailableList);

			map.put("result", resultMap);
			map.put("errorCode", 1);
			map.put("errorMsg", "성공");
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("errorCode", -1);
			map.put("errorMsg", e1.getMessage());
		}
		return map;
	}
}
