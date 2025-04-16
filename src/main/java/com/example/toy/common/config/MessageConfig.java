package com.example.toy.common.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/*@Configuration
public class MessageConfig implements WebMvcConfigurer {

    @Bean
    @ConfigurationProperties(prefix = "spring.messages")
    public MessageSourceProperties messageSourceProperties() { return new MessageSourceProperties(); }


    public MessageSource messageSource(MessageSourceProperties properties) {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();

        if (!ObjectUtils.isEmpty(properties.getBasename())) {
            String[] basenames = properties.getBasename().stream()
                    .filter(StringUtils::hasText) // 빈 문자열 필터링
                    .map(StringUtils::trimWhitespace) // 공백 제거
                    .toArray(String[]::new);
            source.setBasenames(basenames);
        }

        if (!ObjectUtils.isEmpty(properties.getEncoding())) {
            source.setDefaultEncoding(properties.getEncoding().name());
        }

        if (!ObjectUtils.isEmpty(properties.getCacheDuration())) {
            source.setCacheSeconds((int) properties.getCacheDuration().getSeconds());
        }

        source.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
        source.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
        source.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());

        return source;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        DefaultMessageCodesResolver resolver = new DefaultMessageCodesResolver();
        resolver.setMessageCodeFormatter(CustomMessageFormatter.CUSTOM_ERROR_CODE);
        return resolver;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator(MessageSource messageSource) {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setValidationMessageSource(messageSource);
        return factoryBean;
    }

    public enum CustomMessageFormatter implements MessageCodeFormatter {
        CUSTOM_ERROR_CODE{
            @Override
            public String format(
                    String errorCode, @Nullable String objectName, @Nullable String field) {
                return DefaultMessageCodesResolver.Format.toDelimitedString(
                        "msg", "valid", errorCode, null);
            }
        },
    }
}*/

@Configuration
public class MessageConfig {

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource =
        new ReloadableResourceBundleMessageSource();
    messageSource.setBasenames("classpath:label/labels", "classpath:message/messages");
    messageSource.setDefaultEncoding("UTF-8");
    messageSource.setCacheSeconds(3600); // 1 hour
    return messageSource;
  }
}
