package com.hpl.nownew.mapping;

import com.hpl.nownew.MethodInfo;

import java.util.List;

public interface MethodInfoDao {
    List<MethodInfo> getMethodInfo();
    void insertMethodInfo(MethodInfo drugname);
    void insertBatch(List<MethodInfo> data);
    List<MethodInfo> getAll();
    List<MethodInfo> findByName(String name);
}
