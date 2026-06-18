// import ExportJsonExcel from "js-export-excel";

// export function exportExcel(tableData) {
//     // 导出Excel表格

//     option = {},
//     dataList = tableData;
//     let dataTable = []; //   dataTable代表excel文件中的数据内容
    
//     if (dataList) {
//         for (let i in dataList) {
//             let obj = {
//                 时间: dataList[i].time,
//                 类型: dataList[i].type,
//                 文章内容: dataList[i].title,
//             };

//             dataTable.push(obj); //   设置excel每列获取的数据源
//         }
//     }
//     this.option.fileName = "用户个人日志"; //excel文件名
//     //excel文件数据
//     this.option.datas = [
//         {
//             //   excel文件的数据源
//             sheetData: dataTable,
//             //   excel文件sheet的表名
//             sheetName: "sheet",
//             //   excel文件表头名
//             sheetHeader: ["时间", "类型", "文章内容"],
//             //   excel文件列名
//             sheetFilter: ["时间", "类型", "文章内容"],
//             columnWidths: ['10', '10', '20']//excel列宽度设置
//         },
//     ];
//     this.Ture();
// }

// export function Ture() {
//     //   创建ExportJsonExcel实例对象
//     let toExcel = new ExportJsonExcel(this.option);
//     //   调用保存方法
//     toExcel.saveExcel();
// };
