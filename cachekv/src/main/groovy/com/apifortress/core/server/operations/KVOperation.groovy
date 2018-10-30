package com.apifortress.core.server.operations
import com.simonepezzano.wstest.assertions.WSOperation
import com.simonepezzano.wstest.exceptions.ExceptionUtils
import groovy.util.slurpersupport.GPathResult
import net.sf.ehcache.Cache
import net.sf.ehcache.CacheManager
import net.sf.ehcache.Element
import net.sf.ehcache.config.CacheConfiguration

/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * */
class KVOperation extends WSOperation {

    public static final String ID = 'kv'

    private static Cache kvCache

    String key
    String action
    String object
    String var

    public KVOperation(){
        super()
        initCache()
    }
    public void init(GPathResult res){
        action = res.getProperty('@action').toString()
        key = res.getProperty('@key').toString()
        object = res.getProperty('@object').toString()
        var = res.getProperty('@var').toString()
    }

    private static synchronized initCache(){
        if(!kvCache) {
            kvCache = new Cache(new CacheConfiguration('kvCache',100).eternal(false).timeToLiveSeconds(86400))
            CacheManager.getInstance().addCache(kvCache)
            kvCache = CacheManager.getInstance().getCache('kvCache')
        }
    }

    @Override
    boolean evaluate() {
        try {
            long companyId = runner.getContext().get('companyId')
            getRunner().getEvaluator().setScope(getRunner().scope)
            final String k = getRunner().getEvaluator().evaluateTemplate(key)
            final String finalKey = String.valueOf(companyId) + '_' + k
            switch (action) {
                case 'set':
                    def obj = evaluateExpression(object)
                    kvCache.put(new Element(finalKey,obj))
                    break
                case 'push':
                    def obj = evaluateExpression(object)
                    def item = kvCache.get(finalKey)?.getObjectValue()
                    if (item == null || !(item instanceof List))
                        item = []
                    (item as List).push(obj)
                    kvCache.put(new Element(finalKey, item))
                    break
                case 'pop':
                    def item = kvCache.get(finalKey)?.getObjectValue()
                    if (item == null || ((item instanceof List) && item.size() == 0))
                        return null
                    def res = (item as List).pop()
                    kvCache.put(new Element(finalKey, item))
                    runner.scope.set(var, res)
                    break
                case 'load':
                    def item = kvCache.get(finalKey)?.getObjectValue()
                    runner.scope.set(var, item)
                    break
            }
            return true
        }catch(Exception e){
            if(runner.reporter)
                runner.reporter.reportAssertion(this, 'kv('+action+','+key+')', false,0,e.getClass().getSimpleName(),ExceptionUtils.handleExtra(e));
        }
    }

    @Override
    String getAction() {
        return ID
    }

    public String toString(){
        return 'kv('+action+','+key+')'
    }
}
