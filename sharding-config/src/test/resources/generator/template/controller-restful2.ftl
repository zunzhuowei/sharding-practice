package ${controllerPackage};

import ${basePackage}.base.basecontroller.BaseController;
import ${basePackage}.base.baseentity.BaseResult;
import ${basePackage}.enum0.Code;
import ${modelPackage}.${modelNameUpperCamel};
import ${servicePackage}.I${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.HashMap;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller extends BaseController {


    @Resource
    private I${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping
    public BaseResult add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.insertSelective(${modelNameLowerCamel});
        return BaseResult.getBuilder().setSuccess(true).setCode(Code.ERROR_0).build();
    }

    @DeleteMapping("/{id}")
    public BaseResult delete(@PathVariable ${idType} id) {
        ${modelNameLowerCamel}Service.deleteByPrimaryKey(id);
        return BaseResult.getBuilder().setSuccess(true).setCode(Code.ERROR_0).build();
    }

    @PutMapping
    public BaseResult update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.updateByPrimaryKeySelective(${modelNameLowerCamel});
        return BaseResult.getBuilder().setSuccess(true).setCode(Code.ERROR_0).build();
    }

    @GetMapping("/{id}")
    public BaseResult detail(@PathVariable ${idType} id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.selectByPrimaryKey(id);
        return BaseResult.getBuilder().setSuccess(true).setCode(Code.ERROR_0).setContent(${modelNameLowerCamel}).build();
    }

    @GetMapping
    public BaseResult list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.queryListByPage(new HashMap<>());
        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<>(list);
        return BaseResult.getBuilder().setSuccess(true).setCode(Code.ERROR_0).setContent(pageInfo).build();
    }
}
