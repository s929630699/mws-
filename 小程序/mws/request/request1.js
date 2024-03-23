"use strict";
var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
                t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.request = void 0;
var ajaxTimes = 0;
exports.request = function (params) {
    var header = __assign({}, params.header);
    if (params.url.includes("/my/")) {
        header["Authorization"] = wx.getStorageSync("token");
    }
    ajaxTimes++;
    wx.showLoading({
        title: "加载中",
        mask: true
    });
    var baseUrl = "https://api-hmugo-web.itheima.net/api/public/v1";
    return new Promise(function (resolve, reject) {
        wx.request(__assign(__assign({}, params), { header: header, url: baseUrl + params.url, success: function (result) {
                resolve(result.data.message);
            }, fail: function (err) {
                reject(err);
            }, complete: function () {
                ajaxTimes--;
                if (ajaxTimes === 0) {
                    wx.hideLoading();
                }
            } }));
    });
};
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiaW5kZXguanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlcyI6WyJpbmRleC50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7Ozs7OztBQUdBLElBQUksU0FBUyxHQUFDLENBQUMsQ0FBQztBQUNILFFBQUEsT0FBTyxHQUFDLFVBQUMsTUFBVTtJQUU5QixJQUFJLE1BQU0sZ0JBQUssTUFBTSxDQUFDLE1BQU0sQ0FBQyxDQUFDO0lBRzlCLElBQUcsTUFBTSxDQUFDLEdBQUcsQ0FBQyxRQUFRLENBQUMsTUFBTSxDQUFDLEVBQUM7UUFFN0IsTUFBTSxDQUFDLGVBQWUsQ0FBQyxHQUFDLEVBQUUsQ0FBQyxjQUFjLENBQUMsT0FBTyxDQUFDLENBQUM7S0FFcEQ7SUFFRCxTQUFTLEVBQUUsQ0FBQztJQUVaLEVBQUUsQ0FBQyxXQUFXLENBQUM7UUFDYixLQUFLLEVBQUUsS0FBSztRQUNaLElBQUksRUFBRSxJQUFJO0tBQ1gsQ0FBQyxDQUFDO0lBR0gsSUFBTSxPQUFPLEdBQUMsaURBQWlELENBQUM7SUFDaEUsT0FBTyxJQUFJLE9BQU8sQ0FBQyxVQUFDLE9BQU8sRUFBQyxNQUFNO1FBRWhDLEVBQUUsQ0FBQyxPQUFPLHVCQUNOLE1BQU0sS0FFVCxNQUFNLEVBQUMsTUFBTSxFQUViLEdBQUcsRUFBQyxPQUFPLEdBQUMsTUFBTSxDQUFDLEdBQUcsRUFFdEIsT0FBTyxFQUFDLFVBQUMsTUFBVTtnQkFFakIsT0FBTyxDQUFDLE1BQU0sQ0FBQyxJQUFJLENBQUMsT0FBTyxDQUFDLENBQUM7WUFDL0IsQ0FBQyxFQUVELElBQUksRUFBQyxVQUFDLEdBQUc7Z0JBQ1AsTUFBTSxDQUFDLEdBQUcsQ0FBQyxDQUFDO1lBQ2QsQ0FBQyxFQUVELFFBQVEsRUFBQztnQkFDUixTQUFTLEVBQUUsQ0FBQztnQkFDWixJQUFHLFNBQVMsS0FBRyxDQUFDLEVBQUM7b0JBRWYsRUFBRSxDQUFDLFdBQVcsRUFBRSxDQUFDO2lCQUNsQjtZQUNGLENBQUMsSUFDQSxDQUFDO0lBQ0wsQ0FBQyxDQUFDLENBQUE7QUFDSixDQUFDLENBQUEiLCJzb3VyY2VzQ29udGVudCI6WyIvL+WwgeijheS6huS4gOS4quiOt+WPlue9kee7nOaVsOaNrueahOWFrOWFseaWueazle+8jOWIqeeUqHd4LnJlcXVlc3QoKSBBUEnmnaXlrp7njrDmlbDmja7or7fmsYJcclxuLy/lpJbpg6jpgJrov4fosIPnlKjor6XmqKHlnZfnmoRyZXF1ZXN05a6e546w6K+35rGC77yM6ZyA5o+Q5L6bQVBJ5o6l5Y+j55qEdXJs5Zyw5Z2AXHJcbi8vIOWQjOaXtuWPkemAgeW8guatpeS7o+eggeeahOasoeaVsFxyXG5sZXQgYWpheFRpbWVzPTA7XHJcbmV4cG9ydCBjb25zdCByZXF1ZXN0PShwYXJhbXM6YW55KT0+e1xyXG4gIC8vIOWIpOaWrSB1cmzkuK3mmK/lkKbluKbmnIkgL215LyDor7fmsYLnmoTmmK/np4HmnInnmoTot6/lvoQg5bim5LiKaGVhZGVyIHRva2VuXHJcbiAgbGV0IGhlYWRlcj17Li4ucGFyYW1zLmhlYWRlcn07XHJcblxyXG4gIC8v5p+Q5Lqb5oyH5a6aQVBJ6K+35rGC5pe26ZyA6KaB5pC65bim5a6J5YWo5Luk54mMdG9rZW5cclxuICBpZihwYXJhbXMudXJsLmluY2x1ZGVzKFwiL215L1wiKSl7XHJcbiAgICAvLyDmi7zmjqVoZWFkZXIg5bim5LiKdG9rZW7vvIjnlKjmiLfnmbvlvZXml7bnvJPlrZjlnKjmnKzlnLDvvIzmraTlpITkvb/nlKh3eC5nZXRTdG9yYWdlU3luY+WKn+iDveiOt+WPlu+8iVxyXG4gICAgaGVhZGVyW1wiQXV0aG9yaXphdGlvblwiXT13eC5nZXRTdG9yYWdlU3luYyhcInRva2VuXCIpO1xyXG4gICAgLy/lj6/ku6XliKnnlKjlvq7kv6HlsI/nqIvluo/nmoR3eC5zZXRTdG9yYWdlU3luYyhzdHJpbmcga2V5LGFueSBkYXRhKeWcqOeUqOaIt+eZu+mZhuaIkOWKn+aXtuWwhuiOt+WPlnRva2Vu57yT5a2Y5LiL5p2lXHJcbiAgfVxyXG5cclxuICBhamF4VGltZXMrKztcclxuICAvLyDmmL7npLrliqDovb3kuK0g5pWI5p6cXHJcbiAgd3guc2hvd0xvYWRpbmcoe1xyXG4gICAgdGl0bGU6IFwi5Yqg6L295LitXCIsXHJcbiAgICBtYXNrOiB0cnVlXHJcbiAgfSk7XHJcbiAgIFxyXG4gIC8vIOWumuS5ieWFrOWFseeahHVybFxyXG4gIGNvbnN0IGJhc2VVcmw9XCJodHRwczovL2FwaS1obXVnby13ZWIuaXRoZWltYS5uZXQvYXBpL3B1YmxpYy92MVwiO1xyXG4gIHJldHVybiBuZXcgUHJvbWlzZSgocmVzb2x2ZSxyZWplY3QpPT57XHJcbiAgICAvL+iwg+eUqHd4LnJlcXVlc3QoKei/m+ihjOivt+axglxyXG4gICAgd3gucmVxdWVzdCh7XHJcbiAgICAgLi4ucGFyYW1zLFxyXG4gICAgIC8v6K6+572u6K+35rGCaGVhZGVyXHJcbiAgICAgaGVhZGVyOmhlYWRlcixcclxuICAgICAvL+iuvue9ruivt+axgnVybFxyXG4gICAgIHVybDpiYXNlVXJsK3BhcmFtcy51cmwsXHJcbiAgICAgLy/or7fmsYLmiJDlip/ml7bmiafooYznmoTku6PnoIFcclxuICAgICBzdWNjZXNzOihyZXN1bHQ6YW55KT0+e1xyXG4gICAgICAgICAvL+i/lOWbnuaOpeWPo+i/lOWbnue7k+aenOeahG1lc3NhZ2Xpg6jliIbvvIzmoLnmja7lrp7pmYXmjqXlj6PlhrPlrppcclxuICAgICAgIHJlc29sdmUocmVzdWx0LmRhdGEubWVzc2FnZSk7XHJcbiAgICAgfSxcclxuICAgICAvL+ivt+axguWksei0peaXtuaJp+ihjOeahOS7o+eggVxyXG4gICAgIGZhaWw6KGVycik9PntcclxuICAgICAgIHJlamVjdChlcnIpO1xyXG4gICAgIH0sXHJcbiAgICAgLy/or7fmsYLlrozmiJDml7bmiafooYznmoTku6PnoIFcclxuICAgICBjb21wbGV0ZTooKT0+e1xyXG4gICAgICBhamF4VGltZXMtLTtcclxuICAgICAgaWYoYWpheFRpbWVzPT09MCl7XHJcbiAgICAgICAgLy8gIOWFs+mXreato+WcqOetieW+heeahOWbvuagh1xyXG4gICAgICAgIHd4LmhpZGVMb2FkaW5nKCk7XHJcbiAgICAgIH1cclxuICAgICB9XHJcbiAgICB9KTtcclxuICB9KVxyXG59Il19