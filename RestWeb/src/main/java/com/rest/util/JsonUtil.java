package com.rest.util;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * JsonUtil(json 数据的处理类)
 *
 * @author lennylv
 * @version [1.0, 2016年4月29日]
 * @date 2016年4月29日 下午7:01:49
 * @see [相关类/方法]
 * @since version 1.0
 */
public final class JsonUtil {

    /**
     * 私有构造
     */
    private JsonUtil() {
        throw new UnsupportedOperationException();
    }

    private static ObjectMapper objectMapper = null;

    /**
     * 将objectMapper 设置为全局静态缓存，提高调用效率
     */
    static {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }


    /**
     * <br>getObjectMapper(供外部调用OjbectMapper对象)</br>
     *
     * @param
     * @return com.fasterxml.jackson.databind.ObjectMapper
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:05
     * @version 1.0
     * @since 1.0
     */
    public static ObjectMapper getObjectMapper() {

        return objectMapper;
    }


    /**
     * <br>对象转为json</br>
     *
     * @param obj
     * @return java.lang.String
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:06
     * @version 1.0
     * @since 1.0
     */
    public static String obj2json(Object obj) {

        if (obj == null) {
            return null;
        }

        String jsonResult = null;
        try {
            jsonResult = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
        }

        return jsonResult;
    }

    /**
     * <br>getJsonValue(从json串中获取某key对应的值)</br>
     *
     * @param jsonSrc, jsonKey
     * @return java.lang.String
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:22
     * @version 1.0
     * @since 1.0
     */
    public static String getJsonValue(String jsonSrc, String jsonKey) {

        if (StringUtils.isEmpty(jsonSrc) || StringUtils.isEmpty(jsonKey)) {
            return null;
        }

        JsonNode node = JsonUtil.json2obj(jsonSrc, JsonNode.class);
        if (node == null) {
            return null;
        }

        JsonNode dataNode = node.get(jsonKey);
        if (null == dataNode) {
            return null;
        }

        return dataNode.toString();
    }

    /**
     * <br>getJsonAsBool(从json串中获取某key对应的值)</br>
     *
     * @param jsonSrc, jsonKey
     * @return boolean
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:23
     * @version 1.0
     * @since 1.0
     */
    public static boolean getJsonAsBool(String jsonSrc, String jsonKey) {

        JsonNode node = JsonUtil.json2obj(jsonSrc, JsonNode.class);
        JsonNode dataNode = node.get(jsonKey);

        return dataNode.asBoolean();
    }

    /**
     * <br>json2obj(将json串转为对象)</br>
     *
     * @param jsonStr, clazz
     * @return T
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:23
     * @version 1.0
     * @since 1.0
     */
    public static <T> T json2obj(String jsonStr, Class<T> clazz) {

        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }

        T t = null;
        try {
            t = objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
        }

        return t;
    }

    /**
     * <br>obj2node(将对象转换为jsonnode)</br>
     *
     * @param obj
     * @return com.fasterxml.jackson.databind.JsonNode
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:25
     * @version 1.0
     * @since 1.0
     */
    public static JsonNode obj2node(Object obj) {

        if (null == obj) {
            return null;
        }

        JsonNode node = null;
        try {
            node = objectMapper.readTree(obj2json(obj));
        } catch (IOException e) {
        }

        return node;
    }

    /**
     * <br> obj2T(将一般对象转为泛型)</br>
     *
     * @param obj, clazz
     * @return T
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:24
     * @version 1.0
     * @since 1.0
     */
    public static <T> T obj2T(Object obj, Class<T> clazz) {

        if (null == obj) {
            return null;
        }

        T t = null;
        try {
            t = objectMapper.readValue(obj2json(obj), clazz);
        } catch (IOException e) {
        }

        return t;
    }

    /**
     * <br>node2obj(将jsonNode转为一般对象)</br>
     *
     * @param node, clazz
     * @return T
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:26
     * @version 1.0
     * @since 1.0
     */
    public static <T> T node2obj(JsonNode node, Class<T> clazz) throws IOException {

        if (null == node) {
            return null;
        }

        return json2obj(obj2json(node), clazz);
    }

    /**
     * <br>json2map(将json串转为map对象)</br>
     *
     * @param jsonStr, clazz
     * @return java.util.Map<java.lang.String,T>
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:27
     * @version 1.0
     * @since 1.0
     */
    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) {

        // 入參校驗
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }

        Map<String, Map<String, Object>> map = null;
        try {
            map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
            });
        } catch (IOException e) {
        }

        // 非空校验
        if (CollectionUtils.isEmpty(map)) {
            return null;
        }
        Map<String, T> result = new HashMap<String, T>();
        for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }

        return result;
    }

    /**
     * <br>node2list(jsonnode 转list对象)</br>
     *
     * @param jsonNode, clazz
     * @return java.util.List<T>
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:27
     * @version 1.0
     * @since 1.0
     */
    public static <T> List<T> node2list(JsonNode jsonNode, Class<T> clazz) throws IOException {

        if (null == jsonNode || !jsonNode.isArray()) {
            return null;
        }

        List<T> tList = new ArrayList();
        for (Iterator<JsonNode> nodeIt = jsonNode.iterator(); nodeIt.hasNext(); ) {
            JsonNode node = nodeIt.next();
            T t = node2obj(node, clazz);
            tList.add(t);
        }

        return tList;
    }

    /**
     * <br>json2mapWithList(字符串转map，并且map的value为list)</br>
     *
     * @param jsonStr, clazz
     * @return java.util.Map<java.lang.String,java.util.List<T>>
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:28
     * @version 1.0
     * @since 1.0
     */
    public static <T> Map<String, List<T>> json2mapWithList(String jsonStr, Class<T> clazz) {

        // 入參校驗
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }

        Map<String, Map<String, List<Object>>> map = null;
        try {
            map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, List<T>>>() {
            });
        } catch (IOException e) {
        }

        // 非空校验
        if (CollectionUtils.isEmpty(map)) {
            return null;
        }
        Map<String, List<T>> result = new HashMap();
        for (Entry<String, Map<String, List<Object>>> entry : map.entrySet()) {
            List<T> tList = new ArrayList();
            @SuppressWarnings("unchecked")
            List<Object> obList = (List<Object>) entry.getValue();
            for (Object o : obList) {
                T t = objectMapper.convertValue(o, clazz);
                tList.add(t);
            }
            result.put(entry.getKey(), tList);
        }

        return result;
    }

    /**
     * <br>json2list( 将json串转为list对象)</br>
     *
     * @param jsonStr, clazz
     * @return java.util.List<T>
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:28
     * @version 1.0
     * @since 1.0
     */
    public static <T> List<T> json2list(String jsonStr, Class<T> clazz) {

        // 入參校驗
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }

        List<Map<String, Object>> list = null;
        try {
            list = objectMapper.readValue(jsonStr, new TypeReference<List<T>>() {
            });
        } catch (IOException e) {
        }

        // 非空校验
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<T> result = new ArrayList<T>();
        for (Map<String, Object> map : list) {
            result.add(map2pojo(map, clazz));
        }
        return result;

    }

    /**
     * <br>map convert to javaBean</br>
     *
     * @param map, clazz
     * @return T
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:29
     * @version 1.0
     * @since 1.0
     */
    private static <T> T map2pojo(@SuppressWarnings("rawtypes") Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * <br>simpleJson2list(基本数据类型转换成list)</br>
     *
     * @param jsonStr, clazz
     * @return java.util.List<T>
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:29
     * @version 1.0
     * @since 1.0
     */
    public static <T> List<T> simpleJson2list(String jsonStr, Class<T> clazz) {

        try {
            List<T> list =
                    objectMapper.readValue(jsonStr, objectMapper.getTypeFactory()
                            .constructCollectionType(List.class, clazz));
            return list;
        } catch (JsonParseException e) {
            return null;
        } catch (JsonMappingException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * <br>json2node(json字符转为JsonNode对象)</br>
     *
     * @param json
     * @return com.fasterxml.jackson.databind.JsonNode
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:30
     * @version 1.0
     * @since 1.0
     */
    public static JsonNode json2node(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (IOException e) {
            return null;
        }
    }
}
