// JavaScript Document
$(function() {
    // 数字字符串校验
    jQuery.validator.addMethod("isNumericText", function(value, element) {
        var regex = /^[0-9]+$/;
        return this.optional(element) || regex.test(value);
    }, "输入的内容只能含有数字字符");
    
    // 普通字符串校验
    jQuery.validator.addMethod("isAlphaNumericText", function(value, element) {
        var regex = /^[0-9a-zA-Z]+$/;
        return this.optional(element) || regex.test(value);
    }, "输入的内容只能含有数字字符或英文字母");
    
    // X字符校验
    jQuery.validator.addMethod("isXChars", function(value, element) {
        //var regex = /^[(\ )(\#)(\()(\))(\+)(\,)(\-)(\.)(\/)(0-9)(\:)(\;)(\?)(\@)(A-Z)(\_)(a-z)(\{)(\})]+$/;
        var regex = /^[(\ )(\#)(\()(\))(\+)(\,)(\-)(\.)(0-9)(\;)(\?)(\@)(A-Z)(\_)(a-z)]+$/;
        return this.optional(element) || regex.test(value);
    }, "输入的内容不能含有非X字符");
    
    // 非中文字符校验
    jQuery.validator.addMethod("nonChineseChars", function(value, element) {
        var regex = /^[^\\u4e00-\\u9fa5]+$/;
        return this.optional(element) || regex.test(value);
    }, "输入的内容不能含有中文字符");
    
    // 最大字符数校验（中文算两个字符）
    jQuery.validator.addMethod("maxlengthExtend", function(value, element, param) {
        var length = value.length;
        for (var i = 0; i < value.length; i++) {
            if (value.charCodeAt(i) > 127) {
                length++;
            }
        }
        return this.optional(element) || length <= param;
    }, $.validator.format("最多可以输入{0}个字符（中文算两个字符）"));
    
    // 最小字符数校验（中文算两个字符）
    jQuery.validator.addMethod("minlengthExtend", function(value, element, param) {
        var length = value.length;
        for (var i = 0; i < value.length; i++) {
            if (value.charCodeAt(i) > 127) {
                length++;
            }
        }
        return this.optional(element) || length >= param;
    }, $.validator.format("至少输入{0}个字符（中文算两个字符）"));
    
    // 固定字符数校验（中文算一个字符）
    jQuery.validator.addMethod("fixedLength", function(value, element, param) {
        var length = value.length;
        return this.optional(element) || length == param;
    }, $.validator.format("固定输入{0}个字符（中文算一个字符）"));
    
    // 固定字符数校验（中文算两个字符）
    jQuery.validator.addMethod("fixedLengthExtend", function(value, element, param) {
        var length = value.length;
        for (var i = 0; i < value.length; i++) {
            if (value.charCodeAt(i) > 127) {
                length++;
            }
        }
        return this.optional(element) || length == param;
    }, $.validator.format("固定输入{0}个字符（中文算两个字符）"));
    
    // 【用法】requiredExtend: ["#field1", "#field2", "#field3", ...]
    // 【说明】被校验要素所关联的要素中有一项不为空时，被校验要素也不能为空。
    jQuery.validator.addMethod("requiredExtend", function(value, element, param) {
        var result = false;
        for (var i = 0; i < param.length; i++) {
            var _value = document.getElementsByName(param[i])[0].value;
            if (_value.length > 0) {
                result = true;
                break;
            }
          }
        var length = value.length;
        return !result || (result && length > 0);
    }, $.validator.format("这是必填项"));
    
    // 【用法】requiredRelate: ["#field1", "#field2", "#field3", ...]
    // 【说明】被校验要素和所关联的要素要么都填，要么都不填。
    jQuery.validator.addMethod("requiredRelate", function(value, element, param) {
        var result = false;
        for (var i = 0; i < param.length; i++) {
            var _value = document.getElementsByName(param[i])[0].value;
            if (_value.length > 0) {
                result = true;
                break;
            }
          }
        var length = value.length;
        return !result || (result && length > 0);
    }, $.validator.format("这是必填项"));
    
    // 【用法】maxlengthRelate: [35, "#field1", "#field2", "#field3", ...]
    // 【说明】被校验要素和所关联的要素的值长度之和不能超过指定的长度。
    jQuery.validator.addMethod("maxlengthRelate", function(value, element, param) {
        var length = value.length;
        for (var i = 1; i < param.length; i++) {
            length += document.getElementsByName(param[i])[0].value.length;
          }
        return this.optional(element) || length <= param[0];
    }, $.validator.format("总字符数不能超过{0}个字符"));
    
    /*
     * 错误代码：T26
     * 校验规则：参考号不能以斜杠“/”开始或结束，也不能包含双斜杠“//”。
     */
    jQuery.validator.addMethod("T26", function(value, element) {
        return this.optional(element)
                || (value.indexOf("/") != 0 && value.lastIndexOf("/") != value.length - 1 && value.indexOf("//") == -1);
    }, "参考号不能以斜杠“/”开始或结束，也不能包含双斜杠“//”");
    
    // BIC码校验
    jQuery.validator.addMethod("isBIC", function(value, element) {
        var regex = /^[A-Z]{6}[A-Z0-9]{2}[X]{3}$/;
        return this.optional(element) || regex.test(value);
    }, "请填写合法的BIC码");
    
    // BIC2码校验
    jQuery.validator.addMethod("isBIC2", function(value, element) {
        var regex = /^[A-Z]{6}[A-Z0-9]{5}$/;
        return this.optional(element) || regex.test(value);
    }, "请填写合法的BIC2码");
    
    // BIC3码校验
    jQuery.validator.addMethod("isBIC3", function(value, element) {
        var regex = /^[0-9]{4}$/;
        return this.optional(element) || regex.test(value);
    }, "请填写合法的BIC3码");
    
    // BIC4码校验
    jQuery.validator.addMethod("isBIC4", function(value, element) {
        var regex = /^[A-Z]{6}[A-Z0-9]{2}[X]{3}$/;
        var regex2 = /^[0-9]{4}$/;
        return this.optional(element) || regex.test(value) || regex2.test(value);
    }, "请填写合法的BIC4码");
    
    // 处理码校验
    jQuery.validator.addMethod("isPrcCd", function(value, element) {
        // 处理码编码规则：系统类型（1位）+节点类型（1位）+信息类型（1位）+处理结果（1位）+信息编号（5位）。
        var regex = /^[RCEMUF]{1}[012]{1}[IWOS]{1}[0-9]{1}[0-9A-Z]{2}[0-9]{3}$/;
        var result = false;
        if (value == "XXXXXXXXX" || value == "PC0000000" || value == "PC9999999") {
            result = true;
        }
        return this.optional(element) || regex.test(value) || result;
    }, "请填写合法的处理码");
});
