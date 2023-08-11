package kr.co.seoulit.logistics.busisvc.sales.service;

import kr.co.seoulit.logistics.busisvc.logisales.to.ContractInfoTO;
import kr.co.seoulit.logistics.busisvc.sales.to.DeliveryInfoTO;
import kr.co.seoulit.logistics.busisvc.sales.to.QuarterTO;
import kr.co.seoulit.logistics.busisvc.sales.to.ReverseTO;
import kr.co.seoulit.logistics.busisvc.sales.to.SalesPlanTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface SalesService {

    // SalesPlanApplicationServiceImpl
    public ArrayList<ContractInfoTO> getDeliverableContractList(HashMap<String, String> ableSearchConditionInfo);

    public ArrayList<SalesPlanTO> getSalesPlanList(String dateSearchCondition, String startDate, String endDate);

    public HashMap<String, Object> batchSalesPlanListProcess(ArrayList<SalesPlanTO> salesPlanTOList);

    public HashMap<String, Object> batchDeliveryListProcess(List<DeliveryInfoTO> deliveryTOList);

    public HashMap<String, Object> deliver(String contractDetailNo);

    public ArrayList<DeliveryInfoTO> getDeliveryInfoList();

    public ArrayList<ReverseTO> getReturnAbleList(String startDate, String endDate);

    public void insertReturnList(List<ReverseTO> reverseList);

    public ArrayList<QuarterTO> getSalesQuaChart();
}
