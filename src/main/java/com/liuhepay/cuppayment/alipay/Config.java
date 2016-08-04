package com.liuhepay.cuppayment.alipay;

public class Config {
    // 开发者应用私钥。java配置PKCS8格式，PHP/.Net语言配置rsa_private_key.pem文件中原始私钥。
    public static final String RSA_RRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANIjF+GG7oDmesBrm523/1VKEKwaD6jpnLiAxJqISKDb4QovDXJvB7cEoDeYaFlAKZcnfX2nYEJlvGpIA02GwvvBZeKg629sa4aaR3iKTDLdRukcgUbHSJpftGfxtd9GPn7W7Mf5rp6OGMCu/eOOvlnPziN7LPUWk4v1fIIxqcwrAgMBAAECgYAW1Fkz10TyHBgZ75boe6TxzyogiHRLFZZBNTwmzceWMk0wngwH8qgT6hy97YDosV/TXCb2D8kVbZgas9goBAwKu3vJxP5GIJ4It7CbMsU3Q9x2l5DKSWYesZz/WomcN3Ye7mFhpLtoL9FAAksxKZm6hjE0V9gOzUtOOQ7NSuesgQJBAO+bU0LPTsI7afnpB1sfn7wsxos2kK3qJ0w5aCfXsv9Ol7WgG5Dty1srLMova4YmL+7AQ1TjJrdmddOkQnzss70CQQDgg5ySK9XrC4iSzV4UHvuBCjoCW1PvW9t/yccj3VrWGRfd+vuUAV4Damt/J4Nd625XI+7IElcYoOEMqpLF0YoHAkBl8RTEiQT+OF8HV7CsgVDps7cpRabah5P+pzH8OZZLbzm2qY7w42swt7bPsssxgQ/FqYE8HYS8ZNhemjU7h02VAkBpT7Lgqak1EHrmiWhovcOxWq+tLVOowgiq32YGv6z9IlYcrL7XRPDpX0C/KzSmmGlwfI1j0Z6DEeueemBQZCJ/AkEAllVc7mqSNMzNgVE8/kVjdp1ngea4+WjFFl1xhEI+IUivIK0y3TWgIlWpzcqjt6yf7W/VhqB05ubv6p2lYGrjWQ==";
    // 接口请求网关。当面付支付、查询、退款、撤销接口中为固定值
    public static final String URL = "https://openapi.alipay.com/gateway.do";
    // 商户应用APPID，只要您的应用中包含当面付接口且是开通状态，就可以用此应用对应的appid。开发者可登录开放平台-管理中心-对应应用中查看
    public static final String APPID = "2016070101571449";
    // 编码字符集。默认 utf-8
    public static final String CHARSET = "utf-8";
    // 返回格式。默认json
    public static final String FORMAT = "json";
    // 支付宝公钥，用于获取同步返回信息后进行验证，验证是否是支付宝发送的信息。
    public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
}