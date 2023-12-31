package kr.co.seoulit.logistics.logiinfosvc.compinfo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.*;

public interface CompInfoService {

	public ArrayList<CodeDetailTO> getDetailCodeList(String divisionCode);

	public ArrayList<CodeTO> getCodeList();

	public Boolean checkCodeDuplication(String divisionCode, String newDetailCode);

	public HashMap<String, Object> batchCodeListProcess(ArrayList<CodeTO> codeList);

	public HashMap<String, Object> batchDetailCodeListProcess(ArrayList<CodeDetailTO> detailCodeList);

	public HashMap<String, Object> changeCodeUseCheckProcess(ArrayList<CodeDetailTO> detailCodeList);

	public ArrayList<AddressTO> getAddressList(String sidoName, String searchAddressType, String searchValue, String mainNumber);
	
	public void addCodeInFormation(ArrayList<CodeTO>  CodeTOList);	
	
	public ArrayList<CodeDetailTO> getCodeDetailList(String CodeDetail);

    public ArrayList<CustomerTO> getCustomerList();

    public HashMap<String, Object> batchCustomerListProcess(ArrayList<CustomerTO> customerList);

    public ArrayList<FinancialAccountAssociatesTO> getFinancialAccountAssociatesList(String searchCondition,
                                                                                     String workplaceCode);

    public HashMap<String, Object> batchFinancialAccountAssociatesListProcess(
            ArrayList<FinancialAccountAssociatesTO> financialAccountAssociatesList);

    public ArrayList<CompanyTO> getCompanyList();

    public ArrayList<WorkplaceTO> getWorkplaceList(String companyCode);

    public ArrayList<DepartmentTO> getDepartmentList(String searchCondition, String companyCode,
                                                     String workplaceCode);

    public HashMap<String, Object> batchCompanyListProcess(ArrayList<CompanyTO> companyList);

    public HashMap<String, Object> batchWorkplaceListProcess(ArrayList<WorkplaceTO> workplaceList);

    public HashMap<String, Object> batchDepartmentListProcess(ArrayList<DepartmentTO> departmentList);
    
    public ArrayList<LatLngTO> getLatLngList(String wareHouseCodeNo);
    
    public ArrayList<ImageTO> getDetailItemList(String itemGroupCodeNo);
    
    public ArrayList<EstimateReportTO> getEstimateReport(String estimateNo);

    public ArrayList<ContractReportTO> getContractReport(String contractNo);
    
    //board
	public ArrayList<BoardTO> getBoardList();
	
	public void addBoard(BoardTO board);
	
	public BoardTO getBoard(int board_seq);
	
	public BoardTO getBoard(String sessionId,int board_seq);
	
	public void changeHit(int board_seq);
	
	public int getRowCount();
	
	public ArrayList<BoardTO> getBoardList(int sr, int er);
	
	public void removeBoard(int board_seq);

	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ거래원장//
	public CustomerInfoTO getCustomerInfo(String customerCode);
	public ArrayList<DealTO> getDealList(String customerCode);
	public DealDetailTO getDealDetailInfo(String dealCode);
	public void batchCustomer(List<CustomerTO> customerList);
	public void updateDealDetailInfo(DealDetailTO to);
	public PaymentStatusTO getPaymentStatus(String customerCode);
}
