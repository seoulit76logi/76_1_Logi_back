package kr.co.seoulit.logistics.logiinfosvc.compinfo.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.*;
import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.util.BoardFile;

@Mapper
public interface CompInfoMapper {

	//Address
	
	public String selectSidoCode(String sidoName);
	
	public ArrayList<AddressTO> selectRoadNameAddressList(HashMap<String, String> map);
	
	public ArrayList<AddressTO> selectJibunAddressList(HashMap<String, String> map);

	//Company
	
	public ArrayList<CompanyTO> selectCompanyList();
	
	public void insertCompany(CompanyTO TO);
	
	public void updateCompany(CompanyTO TO);

	public void deleteCompany(CompanyTO TO);
	
	//Customer
	
	public ArrayList<CustomerTO> selectCustomerListByCompany();

	public ArrayList<CustomerTO> selectCustomerListByWorkplace(String workplaceCode);
	
	public void insertCustomer(CustomerTO TO);

	public void updateCustomer(CustomerTO TO);

	public void deleteCustomer(CustomerTO TO);

	public ArrayList<CustomerTO> selectCustomerListByItem(String itemGroupCode);
	
	//Department

	public ArrayList<DepartmentTO> selectDepartmentListByCompany(String companyCode);

	public ArrayList<DepartmentTO> selectDepartmentListByWorkplace(String workplaceCode);

	public void insertDepartment(DepartmentTO TO);

	public void updateDepartment(DepartmentTO TO);

	public void deleteDepartment(DepartmentTO TO);

	//FinancialAccountAssociates

	public ArrayList<FinancialAccountAssociatesTO> selectFinancialAccountAssociatesListByCompany();

	public ArrayList<FinancialAccountAssociatesTO> selectFinancialAccountAssociatesListByWorkplace(
			String workplaceCode);

	public void insertFinancialAccountAssociates(FinancialAccountAssociatesTO TO);

	public void updateFinancialAccountAssociates(FinancialAccountAssociatesTO TO);

	public void deleteFinancialAccountAssociates(FinancialAccountAssociatesTO TO);

	//Workplace
	
	public ArrayList<WorkplaceTO> selectWorkplaceList(String companyCode);

	public void insertWorkplace(WorkplaceTO TO);
	
	public void updateWorkplace(WorkplaceTO TO);
	
	public void deleteWorkplace(WorkplaceTO TO);
	
	//Report
	public ArrayList<EstimateReportTO> selectEstimateReport(String estimateNo);

	public ArrayList<ContractReportTO> selectContractReport(String contractNo);
	
	//board
	public ArrayList<BoardTO> selectBoardList();
	
	public BoardTO selectBoard(int board_seq);
	
	public void insertBoard(BoardTO board);
	
	public void updateHit(int board_seq);
	
	public int selectRowCount();
	
	public ArrayList<BoardTO> selectBoardList(int sr, int er);
	
	public void deleteBoard(int board_seq);

	public ArrayList<BoardFile> selectBoardFile(int board_seq);

	public void insertReplyBoard(BoardTO board);

	public void insertBoardFile(BoardFile file);

	
	// -- 거래원장 -- //
	public CustomerInfoTO selectCustomerInfo(String customerCode);
	public ArrayList<DealTO> selectDealInfo(String customerCode);
	public DealDetailTO selectDealDetailInfo(String dealCode);
	public void updateDealDetailInfo(DealDetailTO to);
	public PaymentStatusTO selectPaymentStatus(String customerCode);
	
}
