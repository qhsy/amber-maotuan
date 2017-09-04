package com.ichsy.hrys.entity.request;

import java.util.List;

/**
 * 功能：修改领域标签
 * ＊创建者：赵然 on 16/8/19 16:33
 * ＊
 */
public class ModifyDomainRequestEntity extends BaseRequest {
     private List<String> domainLabel;

     public List<String> getDomainLabel() {
          return domainLabel;
     }

     public void setDomainLabel(List<String> domainLabel) {
          this.domainLabel = domainLabel;
     }
}
