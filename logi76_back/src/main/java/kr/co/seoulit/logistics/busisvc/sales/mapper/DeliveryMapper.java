package kr.co.seoulit.logistics.busisvc.sales.mapper;

import kr.co.seoulit.logistics.busisvc.sales.to.DeliveryInfoTO;
import kr.co.seoulit.logistics.busisvc.sales.to.QuarterTO;
import kr.co.seoulit.logistics.busisvc.sales.to.ReverseTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface DeliveryMapper {

    public ArrayList<DeliveryInfoTO> selectDeliveryInfoList();

    public void deliver(HashMap<String, Object> map);

    public ArrayList<DeliveryInfoTO> selectDeliveryInfoListByDeliverydate(HashMap<String, String> map);

    public void insertDeliveryResult(DeliveryInfoTO TO);

    public void updateDeliveryResult(DeliveryInfoTO TO);

    public void deleteDeliveryResult(DeliveryInfoTO TO);

    public ArrayList<ReverseTO> selectReturnAbleList(@Param("start") String startDate, @Param("end") String endDate);

    public void insertReturnList(HashMap<String, String> returnMap);

    public ArrayList<QuarterTO> selectSalesQuaChart();

}
