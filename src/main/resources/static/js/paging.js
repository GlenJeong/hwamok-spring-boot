(function ($) {
    $.fn.paging = function (options) {
        let curThis = this; // notice.html this는 자기 자신을 가리킴
        let defaults = {
            pageSize: 2, // 한 페이지당 갯수
            currentPage: 1, // 처음 접속 할 때 현재 페이지
            pageTotal: 0, // 총 데이터 수
            pageBlock: 10, // 10 단위로 끊음, 12페이지까지 있다면 1~10까지만 보여줌

        };
        let subOption = $.extend(true, defaults, options); // defaults, options 합쳐짐
        let goPageFnName = null;

        if(subOption.goPageFnName === undefined || subOption.goPageFnName === null || subOption.goPageFnName === ''){
            goPageFnName = "gopage";
        }else{
            goPageFnName =  subOption.goPageFnName;
        }

        return this.each(function () { // foreach 문법
            let currentPage = subOption.currentPage*1; // 1.0 들어오면 *1하면 정수로 바꿔줌
            let pageSize = subOption.pageSize*1;
            let pageTotal = subOption.pageTotal*1;
            let pageBlock = subOption.pageBlock*1;

            let pageTotalCnt = Math.ceil(pageTotal / pageSize); // 총 페이지의 개수
            let pageBlockCnt = Math.ceil(currentPage / pageBlock); // 12 페이지를 10개 페이지로 끊어서, 몇 개의 블럭이 생기는 지 계산
            let startPage, endPage;
            let html = "";

            if(pageBlock > 1){
                startPage = (pageBlockCnt - 1) * pageBlock + 1;
            }

            if((pageBlockCnt * pageBlock) >= pageTotalCnt){
                endPage = pageTotalCnt;
            }else {
                endPage = pageBlockCnt * pageBlock;
            }
            html += `<nav aria-label="Page navigation example">`;
            html += `<ul class="pagination">`;

            if(startPage > 1){

                html += `<li class="page-item">`;
                html += `<a class="page-link" href="" onclick="${goPageFnName}(1)">`;
                html += `<span aria-hidden="true">&laquo;</span>`;
                html += `</a>`;
                html += `</li>`;
            }

            for(let i = startPage; i <= endPage; i++){
                if(currentPage === 1){
                    html += `<li class="page-item"><a class="page-link">${i}</a></li>`;
                }else {
                    html += `<li class="page-item"><a class="page-link" onclick="${goPageFnName}(${i})">${i}</a></li>`;

                }
            }

            console.log(pageBlockCnt);
            console.log(pageTotalCnt);

            if(endPage < pageTotalCnt){
                html += `<li class="page-item"><a class="page-link" onclick="${goPageFnName}(${endPage+1})" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                        </a></li>`;
            }

            html += `</ul></nav>`;

            $(curThis).empty().append(html);
        });
    };
})(jQuery);