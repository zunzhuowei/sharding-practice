package ${servicePackage}.impl;

import ${mapperPackage}.${modelNameUpperCamel}Mapper;
import ${modelPackage}.${modelNameUpperCamel};
import ${servicePackage}.I${modelNameUpperCamel}Service;
import ${basePackage}.base.baseservice.AbstractBaseService;
import ${basePackage}.base.basemapper.IBaseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by ${author} on ${date}.
 */
@Service
@Transactional
public class ${modelNameUpperCamel}Service extends AbstractBaseService<${modelNameUpperCamel}, ${idType}> implements I${modelNameUpperCamel}Service {

    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    @Override
    @Resource(type = ${modelNameUpperCamel}Mapper.class)
    public void setMapper(IBaseMapper<${modelNameUpperCamel}, ${idType}> mapper) {
        super.mapper = mapper;
    }

}
