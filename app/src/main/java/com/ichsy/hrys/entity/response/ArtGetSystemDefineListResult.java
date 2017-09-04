package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.ArtSystemDefine;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ihesen on 2017/3/14 17:30
 * email: hesen@ichsy.com
 */

public class ArtGetSystemDefineListResult extends BaseResponse {

    public List<ArtSystemDefine> codeDefineList = new ArrayList();
}
