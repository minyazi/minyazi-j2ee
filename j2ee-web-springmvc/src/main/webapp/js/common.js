// JavaScript Document
// 获取单选按钮的值
function getRadioValue(name) {
    var radio = document.getElementsByName(name);
    var value = "";
    for (var i = 0; i < radio.length; i++) {
        if (radio[i].checked == true) {
            value = radio[i].value;
            break;
        }
      }
    return value;
}

// 获取多选框选中的数目
function getCheckboxCount(name) {
    var checkbox = document.getElementsByName(name);
    var count = 0;
    for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {
            count++;
        }
    }
    return count;
}

// 获取多选框的值（带单引号）
function getCheckboxValue(name) {
    var checkbox = document.getElementsByName(name);
    var value = "";
    for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {
            value = value + "'" + checkbox[i].value + "',";
        }
      }
      if (value.length > 0) {
          value = value.substring(0, value.length - 1);
      }
    return value;
}

// 获取多选框的值（不带单引号）
function getCheckboxValue2(name) {
    var checkbox = document.getElementsByName(name);
    var value = "";
    for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {
            value = value + checkbox[i].value + ",";
        }
      }
      if (value.length > 0) {
          value = value.substring(0, value.length - 1);
      }
    return value;
}

// 去掉左右两端的空格
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

// 去掉左边的空格
function ltrim(str) {
    return str.replace(/(^\s*)/g, "");
}

// 去掉右边的空格
function rtrim(str) {
    return str.replace(/(\s*$)/g, "");
}

// 为String类添加三个成员
String.prototype.trim = function() {
    // 去掉左右两端的空格
    return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.ltrim = function() {
    // 去掉左边的空格
    return this.replace(/(^\s*)/g, "");
};
String.prototype.rtrim = function() {
    // 去掉右边的空格
    return this.replace(/(\s*$)/g, "");
};

function formatNull(amt_name) {
    var amt = document.getElementsByName(amt_name)[0].value.trim();
    if (amt == "" || amt == 0) {
        document.getElementsByName(amt_name)[0].value = "";
    }
}

/**
 * 格式化数字
 * 
 * @param number 数字
 * @param integer_length 整数位数
 * @param decimal_length 小数位数
 * @param useComma 整数部分是否用英文逗号隔开（true：隔开，false：不隔开）
 * @param noZero 是否去掉小数部分右边的0（true：去掉，false：不去掉）
 * @returns 格式化之后的数字
 */
function formatNumber(number, integer_length, decimal_length, useComma, noZero) {
    number = number.replace(/,/g, "");
    if (number == "" || isNaN(number) || number <= 0) {
        number = "0";
    }
    
    var decimal_temp = "";
    while (true) {
        if (decimal_temp.length < decimal_length) {
            decimal_temp = decimal_temp + "0";
        } else {
            break;
        }
    }
    
    if (number == 0 || number.indexOf(".") == -1) {
        number = number + "." + decimal_temp;
    }
    
    // 截取整数部分（不包含小数点）
    var integer_part = number.substring(0, number.indexOf("."));
    // 去掉整数部分左边超长的部分
    if (integer_part.length > integer_length) {
        integer_part = integer_part.substring(integer_part.length - integer_length);
    }
    // 去掉整数部分左边的0
    while (true) {
        if (integer_part.length > 0) {
            var temp = integer_part.substring(0, 1);
            if (temp == 0) {
                integer_part = integer_part.substring(1);
            } else {
                break;
            }
        }
    }
    if (integer_length > 0 && integer_part == "") {
        integer_part = "0";
    }
    
    if (useComma && integer_part.length > 3) {
        // 格式化整数部分
        var temp = "";
        while (true) {
            temp = "," + integer_part.substring(integer_part.length - 3) + temp;
            integer_part = integer_part.substring(0, integer_part.length - 3);
            if (integer_part.length <=3) {
                temp = integer_part + temp;
                break;
            }
        }
        integer_part = temp;
    }
    
    // 截取小数部分（不包含小数点）
    var decimal_part = number.substring(number.indexOf("."));
    decimal_part = decimal_part + decimal_temp;
    decimal_part = decimal_part.substring(1);
    decimal_part = decimal_part.substring(0, decimal_length);
    if (noZero) {
        // 去掉小数部分右边的0
        while (true) {
            if (decimal_part.length > 0) {
                var temp = decimal_part.substring(decimal_part.length - 1);
                if (temp == 0) {
                    decimal_part = decimal_part.substring(0, decimal_part.length - 1);
                } else {
                    break;
                }
            }
        }
    }
    
    number = integer_part + "." + decimal_part;
    if (number == ".") {
        number = "";
    }
    return number;
}

function formatAmount(amt_name, cur_name) {
    var amt = document.getElementsByName(amt_name)[0].value.trim();
    var cur = "CNY";
    if (arguments.length > 1) {
        cur = document.getElementsByName(arguments[1])[0].value.trim();
    }
    
    var length = 0; // 金额的小数位数
    if (cur == "USD") { // 美元
        length = 2;
    } else if (cur == "CNY") { // 人民币
        length = 2;
    } else if (cur == "HKD") { // 港币
        length = 2;
    } else if (cur == "JPY") { // 日元
        length = 0;
    } else if (cur == "EUR") { // 欧元
        length = 2;
    } else if (cur == "CAD") { // 加拿大元
        length = 2;
    } else if (cur == "AUD") { // 澳大利亚元
        length = 2;
    } else if (cur == "GBP") { // 英镑
        length = 2;
    } else if (cur == "CHF") { // 瑞士法郎
        length = 2;
    }
    
    document.getElementsByName(amt_name)[0].value = formatNumber(amt, 16, length, false, false);
}

function _formatAmount(amt_name, cur_name) {
    var amt = document.getElementsByName(amt_name)[0].value.trim();
    var cur = "CNY";
    if (arguments.length > 1) {
        cur = document.getElementsByName(arguments[1])[0].value.trim();
    }
    
    var length = 0; // 金额的小数位数
    if (cur == "USD") { // 美元
        length = 2;
    } else if (cur == "CNY") { // 人民币
        length = 2;
    } else if (cur == "HKD") { // 港币
        length = 2;
    } else if (cur == "JPY") { // 日元
        length = 0;
    } else if (cur == "EUR") { // 欧元
        length = 2;
    } else if (cur == "CAD") { // 加拿大元
        length = 2;
    } else if (cur == "AUD") { // 澳大利亚元
        length = 2;
    } else if (cur == "GBP") { // 英镑
        length = 2;
    } else if (cur == "CHF") { // 瑞士法郎
        length = 2;
    }
    
    amt = amt.replace(/,/g, ".");
    amt = formatNumber(amt, 16, length, false, false);
    amt = amt.replace(/\./g, ",");
    document.getElementsByName(amt_name)[0].value = amt;
}

function formatPercentageRate(number_name) {
    var number = document.getElementsByName(number_name)[0].value.trim();
    number = formatNumber(number, 1, 6, false, true);
    if (number.length > 0 && number.substring(number.length - 1) == ".") {
        number = number + "0";
    }
    document.getElementsByName(number_name)[0].value = number;
}

function formatCurrencyAndAmount(number_name) {
    var number = document.getElementsByName(number_name)[0].value.trim();
    number = formatNumber(number, 16, 2, false, false);
    document.getElementsByName(number_name)[0].value = number;
}

function formatCurrencyAndAmountRegCap(number_name) {
    var number = document.getElementsByName(number_name)[0].value.trim();
    number = formatNumber(number, 11, 2, false, false);
    document.getElementsByName(number_name)[0].value = number;
}

function formatPercentTrdRate(number_name) {
    var number = document.getElementsByName(number_name)[0].value.trim();
    number = formatNumber(number, 1, 8, false, true);
    if (number.length > 0 && number.substring(number.length - 1) == ".") {
        number = number + "0";
    }
    document.getElementsByName(number_name)[0].value = number;
}

function formatPercentRate(number_name) {
    var number = document.getElementsByName(number_name)[0].value.trim();
    number = formatNumber(number, 5, 8, false, true);
    if (number.length > 0 && number.substring(number.length - 1) == ".") {
        number = number + "0";
    }
    document.getElementsByName(number_name)[0].value = number;
}

function changePage(name) {
    var field = name.split(".")[1];
    $("tr." + field).css("display", "none");
    $("tr." + field + " select:enabled").attr("disabled", "disabled");
    $("tr." + field + " input:enabled").attr("disabled", "disabled");
    
    var value = document.getElementsByName(name)[0].value;
    if (value.trim() != "") {
        var field_value = field + value;
        $("tr." + field_value).css("display", "");
        $("tr." + field_value + " select:disabled").removeAttr("disabled");
        $("tr." + field_value + " input:disabled").removeAttr("disabled");
    }
}

// 页面跳转
function redirect(goal) {
    document.location.href = location.protocol + "//" +  location.host + "/cpestst/" + goal;
}

// 清空文件域
function clearFile(id) {
    var html = document.getElementById(id).innerHTML;
    document.getElementById(id).innerHTML = html;
}

// 显示提示信息
function showMessage(message) {
    tipsWindown("提示信息","iframe:jsp/message.jsp?message=" + encodeURI(message),"420","220","true","","true","leotheme");
}

// 显示提示信息（删除确认）
function confirmDelete(action) {
    tipsWindown("提示信息","iframe:jsp/delete.jsp?action=" + action,"420","220","true","","true","leotheme");
}

//确认提示（操作确认）
function confirm(action, message_a, message_b) {
    tipsWindown("提示信息","iframe:jsp/confirm.jsp?action=" + action + "&message_a=" + encodeURI(message_a) + "&message_b=" + encodeURI(message_b),"420","220","true","","true","leotheme");
}

function startClock() {
    showTime();
    window.setInterval("showTime()", 1000);
}

function showTime() {
    var date = new Date();
    var year = date.getFullYear() + "年";
    var month = date.getMonth() + 1;
    month = ((month < 10) ? "0" : "") + month;
    month = month + "月";
    var day = date.getDate();
    day = ((day < 10) ? "0" : "") + day;
    day = day + "日";
                
    var hour = date.getHours();
    hour = ((hour < 10) ? "0" : "") + hour;
    var minute = date.getMinutes();
    minute = ((minute < 10) ? "0" : "") + minute;
    var second = date.getSeconds();
    second = ((second < 10) ? "0" : "") + second;
                
    var weekday = date.getDay();
    switch(weekday) {
        case 0:
            weekday = "天";
            break;
        case 1:
            weekday = "一";
            break;
        case 2:
            weekday = "二";
            break;
        case 3:
            weekday = "三";
            break;
        case 4:
            weekday = "四";
            break;
        case 5:
            weekday = "五";
            break;
        case 6:
            weekday = "六";
            break;
    }
    
    var time = year + month + day + " " + hour + ":" + minute + ":" + second + " " + "星期" + weekday;
    var x = document.getElementById("clock").rows[0].cells;
    x[0].innerHTML = time;
}

$(function() {
    $("tr.record").each(function() {
        $(this).addClass("record");
    });
    $("tr.record").mousemove(function() {
        $(this).addClass("over");
    });
    $("tr.record").mouseout(function() {
        $(this).removeClass("over");
    });
    
    $("td.mouseout").each(function() {
        $(this).addClass("mouseout");
    });
    $("td.mouseout").mousemove(function() {
        $(this).addClass("mouseover");
    });
    $("td.mouseout").mouseout(function() {
        $(this).removeClass("mouseover");
    });
    
    $("a.mouseout").each(function() {
        $(this).addClass("mouseout");
    });
    $("a.mouseout").mousemove(function() {
        $(this).addClass("mouseover");
    });
    $("a.mouseout").mouseout(function() {
        $(this).removeClass("mouseover");
    });
    
    /*$(":file").mousedown(function() {
        this.click();
    });*/
    $(":file").keydown(function() {
        return false;
    });
    $(":file").keyup(function() {
        return false;
    });
});
