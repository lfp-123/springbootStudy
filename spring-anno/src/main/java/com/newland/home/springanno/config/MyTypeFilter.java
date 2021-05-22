package com.newland.home.springanno.config;

        import org.springframework.core.io.Resource;
        import org.springframework.core.type.AnnotationMetadata;
        import org.springframework.core.type.ClassMetadata;
        import org.springframework.core.type.classreading.MetadataReader;
        import org.springframework.core.type.classreading.MetadataReaderFactory;
        import org.springframework.core.type.filter.TypeFilter;

        import java.io.IOException;

/**
 * @author ${linfengpeng}
 * @Title: MyTypeFilter
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/9/24 10:30
 */
public class MyTypeFilter implements TypeFilter {

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前类资源信息
        Resource resource = metadataReader.getResource();
        //获取当前类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        String className = classMetadata.getClassName();
        System.out.println(className);
        if(className.contains("Service")){
            return  true;
        }
        return false;



    }
}
