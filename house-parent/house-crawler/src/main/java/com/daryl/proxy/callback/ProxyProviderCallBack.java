package com.daryl.proxy.callback;


import com.daryl.proxy.pojo.CrawlerProxy;

import java.util.List;

/**
 * IP池更新回调
 */
public interface ProxyProviderCallBack {
    public List<CrawlerProxy> getProxyList();

    public void unavailable(CrawlerProxy crawlerProxy);
}
