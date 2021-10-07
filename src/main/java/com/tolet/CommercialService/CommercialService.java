package com.tolet.CommercialService;

public interface CommercialService {
    CommercialSpace createCommercialSpace(CommercialSpace commercialSpace,Integer userId);
    CommercialSpace updateCommercialSpace(CommercialSpace commercialSpace);
    void deleteCommercialSpace(Integer spaceId);
}
