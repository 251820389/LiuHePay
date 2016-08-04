

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.liuhepay.cuppayment.constants;

/**
 * 支付宝服务窗环境常量（demo中常量只是参考，需要修改成自己的常量值）
 * 
 * @author taixu.zqq
 * @version $Id: AlipayServiceConstants.java, v 0.1 2014年7月24日 下午4:33:49 taixu.zqq Exp $
 */
public class AlipayServiceEnvConstants {

    /**支付宝公钥-从支付宝服务窗获取*/
    public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDo3f8LvnDYKP8KAg6qHOjXhGCWW8pliLcxxyEcTM3agyN0ospabSIAYWS6RWfCArt+2BPFnYV2PBUn0PaNG0Mnp8TlVPcrTjWbhvtIwf+5kN2CP9xQOAdQQp3YCbaYVCZ6JOUxQ6Whi9CtNKic5DTJGLG/Iltw1inLEkgKF+dRZwIDAQAB";

    /**签名编码-视支付宝服务窗要求*/
    public static final String SIGN_CHARSET      = "UTF-8";

    /**字符编码-传递给支付宝的数据编码*/
    public static final String CHARSET           = "UTF-8";

    /**签名类型-视支付宝服务窗要求*/
    public static final String SIGN_TYPE         = "RSA";
    
    
    public static final String PARTNER           = "2088911227080000";

    /** 服务窗appId  */
    //TODO !!!! 注：该appId必须设为开发者自己的服务窗id  这里只是个测试id
    public static final String APP_ID            = "2015111200767495";

    //开发者请使用openssl生成的密钥替换此处  请看文档：https://fuwu.alipay.com/platform/doc.htm#2-1接入指南
    //TODO !!!! 注：该私钥为测试账号私钥  开发者必须设置自己的私钥 , 否则会存在安全隐患 
    public static final String PRIVATE_KEY       = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAOjd/wu+cNgo/woC" +
    		"Dqoc6NeEYJZbymWItzHHIRxMzdqDI3SiylptIgBhZLpFZ8ICu37YE8WdhXY8FSfQ" +
    		"9o0bQyenxOVU9ytONZuG+0jB/7mQ3YI/3FA4B1BCndgJtphUJnok5TFDpaGL0K00" +
    		"qJzkNMkYsb8iW3DWKcsSSAoX51FnAgMBAAECgYEAgWMH2UChdpmgy7DE8SRLA6Sb" +
    		"/i9WUAAcud3Iue662lTPGHlzMbc9kzSKQS1bL2NRRnNM2RpjE4TixFLJCzJApvKP" +
    		"XMq+qyUw9oeFkfVm29sDeRX1k8GanCFr+/ewNx9zFyuXrebUowroi9KiJO2sXxK8" +
    		"/LsF66Rg/D6tbr9pSPECQQD7rAsmLDtjbQjk9PhUPux6E74Bfsf4KJTjRo9w8Hoa" +
    		"laqqe6qXDJE5/fCz0S2G2S+Da+wyBSLQXAIKOmfb7As1AkEA7N8qn08QkRAAkZns" +
    		"2/teUjLXFrXDlzYiTy9B7zPiBSHpJdw8Qyd71GDI4d+bdbOV4JvOzhEqHCfaRaQA" +
    		"Q+IhqwJBAJdHaUQ+3kv7pJcL8aGiWT9gUpxPeyBeNHERXvPCeYM1Z+smfvpg4YL8" +
    		"dA2CtV0TfA4Bd27xy2V3iPpg9UEq9+kCQFszPYTi+zzvDVkCC9lS+ijp3DipOSpy" +
    		"NFmKl3dS4ZYaTVs/ZZBxllLaBN46qu9xeqZlNwXORAldKsQfdd6tJUsCQQC6cEWg" +
    		"M8cIiO3z13k7SIJRKAnjzxRvNHXRnn403usRTTfb9bP0x8foha1kj3h/3AW9RDeh" +
    		"/zRKIYlpLcZHE5VE";

    //TODO !!!! 注：该公钥为测试账号公钥  开发者必须设置自己的公钥 ,否则会存在安全隐患
    public static final String PUBLIC_KEY        = "";

    /**支付宝网关*/
    public static final String ALIPAY_GATEWAY    = "https://openapi.alipay.com/gateway.do";

    /**授权访问令牌的授权类型*/
    public static final String GRANT_TYPE        = "authorization_code";
}