package kr.co.seoulit.logistics.purcstosvc.purchase.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Entity
@Table(name="ORDER_INFO")
public class OrderInfoEntity {

        @Id
        private String orderNo;
        private String orderDate;
        private String orderInfoStatus;
        private String orderSort;
        private String itemCode;
        private String itemName;
        private String orderAmount;
        private String inspectionStatus;

        @Transient //체크 상태 그리드의
        private String checked;

}
