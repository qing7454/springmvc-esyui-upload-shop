/*
 *  CustomObjectMapper
 * ?Created?on?2016/6/12 15:35
 * ?
 * ?�汾???????�޸�ʱ��??????????����??????  �޸�����?
 * ?V0.1????   2016/6/12??????    �̺�ǿ???  ?��ʼ�汾?
 * ?V0.2????   
 * ?V0.3 ???   
 * ?V0.4????   
 * ?V0.5????   
 * ?Copyright?(c)?2016?SK��Ŀ��?��Ȩ����?
 * ?SK project team?All?Rights?Reserved.?
 */
package com.sys.mapper;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CustomObjectMapper
 * Created by �̺�ǿ on 2016/6/12.
 */
public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper(){
        CustomSerializerFactory factory = new CustomSerializerFactory();
        factory.addGenericMapping(Date.class, new JsonSerializer<Date>(){
            @Override
            public void serialize(Date value,
                                  JsonGenerator jsonGenerator,
                                  SerializerProvider provider)
                    throws IOException, JsonProcessingException {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                jsonGenerator.writeString(sdf.format(value));
            }
        });
        this.setSerializerFactory(factory);
    }
}
