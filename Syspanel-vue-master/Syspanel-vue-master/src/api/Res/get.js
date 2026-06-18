import request from "../../utils/request"

export function getCriInfo() {
    return request({
        url: '/SystemInfo/getCpu_usage',
        method: 'Get',
        // data: Data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export function getProcessedInfo() {
    return request({
        url: '/process/getProcessedInfo',
        method: 'Get',
        // data: Data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export function getSystemInfoCri() {
    return request({
        url: '/SystemInfo/getSystemInfo',
        method: 'Get',
        // data: Data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export function getWarnInfo(params) {
    console.log(params);
    const queryParams = [];

    if (params.time !== '') {
        queryParams.push(`time=${encodeURIComponent(params.time)}`);
    }
    if (params.value !== '') {
        queryParams.push(`type=${encodeURIComponent(params.value)}`);
    }
    const queryString = queryParams.length > 0 ? `?${queryParams.join('&')}` : '';

    const baseUrl = '/warn/warning_Info';
    const url = baseUrl + queryString;
    return request({
        url: url,
        method: 'Get',
        // data: Data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!Array.isArray(response.data.data)) {
                return Promise.reject(new Error('Invalid data format'));
            }
            // 假设 response.data.totalCount 是从后端获取的总条数（如果后端没有提供，则需要额外处理） 
            let totalCount = response.data.totalCount || response.data.data.length; // 如果后端未提供，则使用返回数据的长度（注意：这仅在未分页时有效） 

            // 根据 page 和 size 进行分页
            const startIndex = (params.page - 1) * params.size;
            const endIndex = Math.min(startIndex + params.size, totalCount);
            const paginatedData = response.data.data.slice(startIndex, endIndex);

            // 格式化分页后的数据 
            const formattedData = paginatedData.map(item => ({
                title: item.warnTitle,
                time: item.logTime.split(' ')[0], // 提取日期部分 
                type: item.warnType,
                message: item.warnContact
            }));

            // 返回一个包含数据和条数的对象 
            return {
                data: formattedData,
                totalCount: totalCount,
            };
        })
        .catch(error => {
            // 处理错误 
            console.error('请求失败:', error);
        });
}
export function outLoginFun(){
    return request({
        url: `/login/exit`,
        method: 'get'
    });  
  }  
  export function getMessages(){

    return request({
        url: '/logs/getLogByName',
        method: 'Get'
    })
    .then(response => {  
        if (!Array.isArray(response.data.data)) {  
            return Promise.reject(new Error('Invalid data format'));  
        }
        
        const typeMap = {  
            '值班': 'success',  
            '巡检': 'warning', // 注意：原始数据中并没有'巡检'，这只是一个示例  
            '故障': 'error'  
        }; 
        const paginatedData = response.data.data;
        console.log(paginatedData);
        const formattedData = paginatedData.map((item, index) => ({  
            id: index + 1, // 使用索引+1作为id  
            type: typeMap[item.content] || 'success', // 尝试从映射中获取type，如果失败则默认为'success'  
            title: item.content, // 假设每个item都有一个title字段  
            description: item.logInfo || '默认描述' // 假设logInfo包含描述，否则使用默认描述  
            // 如果item对象中包含更多你需要的信息，你可以在这里继续添加它们  
        }));  
        console.log(formattedData);

        return {  
            data: formattedData,  
        }; 
    })  
    .catch(error => {  
        // 处理错误  
        console.error('请求失败:', error);  
    });  
}
export function getSetting() {
    return request({
        url: '/MonitorSetting/selectSetting',
        method: 'Get',
        // data: Data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            // if (!Array.isArray(response.data.data)) {
            //     return Promise.reject(new Error('Invalid data format'));
            // }
            const settingData = response.data.data;
            const formattedData = {
                number: settingData.number,
                interval: settingData.collectionInterval,
                cpumin: settingData.cpuWarningValue,
                cpumax: settingData.cpuDangerValue,
                memorymin: settingData.memoryWarningValue,
                memorymax: settingData.memoryDangerValue
            };

            // 返回一个包含数据和条数的对象 
            return {
                status: settingData.status,
                data: formattedData
            };
        })
        .catch(error => {
            // 处理错误 
            console.error('请求失败:', error);
        });
}

export function getWarn_Number() {
    return request({
        url: '/warn/getWarn_Number',
        method: 'Get',
        // data: Data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export function getDanger_Number() {
    return request({
        url: '/warn/getDanger_Number',
        method: 'Get',
        // data: Data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export function getHostInfo() {
    return request({
        url: '/hostInfo/getHostInfo',
        method: 'Get',
        // data: Data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export function getTableListFuns(params) {
    console.log(params);
    const queryParams = [];

    if (params.time !== '') {
        queryParams.push(`time=${encodeURIComponent(params.time)}`);
    }
    if (params.type !== '') {
        queryParams.push(`type=${encodeURIComponent(params.type)}`);

    }
    const queryString = queryParams.length > 0 ? `?${queryParams.join('&')}` : '';

    const baseUrl = '/logs/getLogByName';
    const url = baseUrl + queryString;
    return request({
        url: url,
        method: 'Get'
    })
        .then(response => {
            if (!Array.isArray(response.data.data)) {
                return Promise.reject(new Error('Invalid data format'));
            }
            // 假设 response.data.totalCount 是从后端获取的总条数（如果后端没有提供，则需要额外处理）  
            let totalCount = response.data.totalCount || response.data.data.length; // 如果后端未提供，则使用返回数据的长度（注意：这仅在未分页时有效）  

            // 根据 page 和 size 进行分页  
            const startIndex = (params.page - 1) * params.size;
            const endIndex = Math.min(startIndex + params.size, totalCount);
            const paginatedData = response.data.data.slice(startIndex, endIndex);

            // 格式化分页后的数据  
            const formattedData = paginatedData.map(item => ({
                time: item.createdAt.split('T')[0], // 提取日期部分  
                type: item.content, // 将 userName 映射为 number  
                title: item.logInfo,
            }));

            // 返回一个包含数据和条数的对象  
            return {
                data: formattedData,
                totalCount: totalCount,
            };
        })
        .catch(error => {
            // 处理错误  
            console.error('请求失败:', error);
        });
}

/**  
 * 通过发送请求查询日志数据  
 * @param {Object} params 请求参数  
 * @returns {Promise<{data: any[], totalCount: number}>}
 */
export function getTableListFun(params) {
    console.log(params);
    const queryParams = [];

    if (params.time !== '') {
        queryParams.push(`time=${encodeURIComponent(params.time)}`);
    }

    if (params.number !== '') {
        queryParams.push(`number=${encodeURIComponent(params.number)}`);
    }

    const queryString = queryParams.length > 0 ? `?${queryParams.join('&')}` : '';

    const baseUrl = '/logs/getAllLog';
    const url = baseUrl + queryString;
    return request({
        url: url,
        method: 'Get'
    })
        .then(response => {
            if (!Array.isArray(response.data.data)) {
                return Promise.reject(new Error('Invalid data format'));
            }
            // 假设 response.data.totalCount 是从后端获取的总条数（如果后端没有提供，则需要额外处理）  
            let totalCount = response.data.totalCount || response.data.data.length; // 如果后端未提供，则使用返回数据的长度（注意：这仅在未分页时有效）  

            // 根据 page 和 size 进行分页  
            const startIndex = (params.page - 1) * params.size;
            const endIndex = Math.min(startIndex + params.size, totalCount);
            const paginatedData = response.data.data.slice(startIndex, endIndex);

            // 格式化分页后的数据  
            const formattedData = paginatedData.map(item => ({
                number: item.userName, // 将 userName 映射为 number  
                time: item.createdAt.split('T')[0], // 提取日期部分  
                type: item.content, // 假设 content 字段代表类型  
                title: item.logInfo, // logInfo 字段作为标题  
            }));

            // 返回一个包含数据和条数的对象  
            return {
                data: formattedData,
                totalCount: totalCount,
            };
        })
        .catch(error => {
            // 处理错误  
            console.error('请求失败:', error);
        });

}



// 获取账号数据
/**  
 * 通过发送请求查询用户数据  
 * @param {Object} params 请求参数  
 * @returns {Promise<{data: any[], totalCount: number}>}
 */
export function getAccountDtatFun(params) {

    console.log(params);
    const queryParams = [];

    if (params.number !== '') {
        queryParams.push(`number=${encodeURIComponent(params.number)}`);
    }
    if (params.category !== '') {
        queryParams.push(`role=${encodeURIComponent(params.category)}`);

    }

    const queryString = queryParams.length > 0 ? `?${queryParams.join('&')}` : '';

    const baseUrl = '/user/getAllUser';
    const url = baseUrl + queryString;
    return request({
        url: url,
        method: 'Get'
    })
        .then(response => {
            if (!Array.isArray(response.data.data)) {
                return Promise.reject(new Error('Invalid data format'));
            }
            // 假设 response.data.totalCount 是从后端获取的总条数（如果后端没有提供，则需要额外处理）  
            let totalCount = response.data.totalCount || response.data.data.length; // 如果后端未提供，则使用返回数据的长度（注意：这仅在未分页时有效）  

            // 根据 page 和 size 进行分页  
            const startIndex = (params.page - 1) * params.size;
            const endIndex = Math.min(startIndex + params.size, totalCount);
            const paginatedData = response.data.data.slice(startIndex, endIndex);

            // 格式化分页后的数据  
            const formattedData = paginatedData.map(item => ({
                number: item.userName, // 将 userName 映射为 number  
                time: item.createdAt.split('T')[0], // 提取日期部分  
                permission: item.role === 'admin' ? '管理员' : (item.role === 'guest' ? '普通用户' : item.role),
            }));

            // 返回一个包含数据和条数的对象  
            return {
                data: formattedData,
                totalCount: totalCount,
            };
        })
        .catch(error => {
            // 处理错误  
            console.error('请求失败:', error);
        });


}
export function deleteUserByName(data) {
    return request({
        url: `/user/deleteUserByName?username=${encodeURIComponent(data)}`,
        method: 'get'
    })
}

export function isExistByName(data) {
    return request({
        url: `/user/isExistByName?username=${encodeURIComponent(data)}`,
        method: 'get'
    });
}

export function isExistByEmail(data) {
    return request({
        url: `/user/isExistByEmail?email=${encodeURIComponent(data)}`,
        method: 'get'
    });
}
export function getInfo(){
    return request({
        url: 'user/getUserInfo',
        method: 'Get'
    })
    .then(response => {  
 
        const userData = response.data.data;
  
        // 返回一个包含数据和条数的对象  
        return {  
            username: userData.userName,  
            password: userData.password,
            email:userData.email,
            passtime:userData.outTime,
        }; 
    })  
    .catch(error => {  
        // 处理错误  
        console.error('请求失败:', error);  
    });  

}
export function getUserName(){
    return request({
        url: 'user/getUserInfo',
        method: 'Get'
    })
    .then(response => {  
 
        const userData = response.data.data;
  
        // 返回一个包含数据和条数的对象  
        return {  
            username: userData.userName,  
        }; 
    })  
    .catch(error => {  
        // 处理错误  
        console.error('请求失败:', error);  
    });  

}

export function getMonitorTabDataFun(params) {
    console.log(params);
    const queryParams = [];  

    if (params.number !== '') {  
        queryParams.push(`number=${encodeURIComponent(params.number)}`);  
    }  
    if (params.time !== ''){
        queryParams.push(`time=${encodeURIComponent(params.time)}`);  

    }
  
    const queryString = queryParams.length > 0 ? `?${queryParams.join('&')}` : ''; 

    const baseUrl = '/user/getAllLoginUser';  
    const url = baseUrl + queryString; 
    return request({
        url: url,
        method: 'Get'
    })
    .then(response => {  
        if (!Array.isArray(response.data.data)) {  
            return Promise.reject(new Error('Invalid data format'));  
        } 
        // 假设 response.data.totalCount 是从后端获取的总条数（如果后端没有提供，则需要额外处理）  
        let totalCount = response.data.totalCount || response.data.data.length; // 如果后端未提供，则使用返回数据的长度（注意：这仅在未分页时有效）  
    
        // 根据 page 和 size 进行分页  
        const startIndex = (params.page - 1) * params.size;  
        const endIndex = Math.min(startIndex + params.size, totalCount);  
        const paginatedData = response.data.data.slice(startIndex, endIndex);  
    
        // 格式化分页后的数据  
        const formattedData = paginatedData.map(item => ({  
            number: item.userName, // 将 userName 映射为 number  
            star: item.loginTime.replace('T', ' '), // 替换loginTime中的'T'为一个空格  
            end: item.logoutTime.replace('T', ' '), 
        }));  
  
        // 返回一个包含数据和条数的对象  
        return {  
            data: formattedData,  
            totalCount: totalCount,  
        }; 
    })  
    .catch(error => {  
        // 处理错误  
        console.error('请求失败:', error);  
    });  
}
export function isUserLogin(){
    return request({
        url: `/user/isUserLogin`,
        method: 'get'
    });  
  } 
  export function handleLogout() {  

    // 发送请求到后端清除会话信息  
    // 注意：这里使用了axios作为HTTP客户端，你需要确保已经安装并导入了它  
    return request({
      url: 'login/exit',
      method: 'Get'
  }) 
}  

export function getTableListFunCom(params){
    
    console.log(params);
    const queryParams = [];  

    if (params.type !== '') {  
        queryParams.push(`type=${encodeURIComponent(params.type)}`);  
    }  
    if (params.time !== ''){
        queryParams.push(`time=${encodeURIComponent(params.time)}`);  

    }
  
    const queryString = queryParams.length > 0 ? `?${queryParams.join('&')}` : ''; 

    const baseUrl = '/logs/getAllUserLog';  
    const url = baseUrl + queryString; 
    return request({
        url: url,
        method: 'Get'
    })
    .then(response => {  
        if (!Array.isArray(response.data.data)) {  
            return Promise.reject(new Error('Invalid data format'));  
        } 
        // 假设 response.data.totalCount 是从后端获取的总条数（如果后端没有提供，则需要额外处理）  
        let totalCount = response.data.totalCount || response.data.data.length; // 如果后端未提供，则使用返回数据的长度（注意：这仅在未分页时有效）  
    
        // 根据 page 和 size 进行分页  
        const startIndex = (params.page - 1) * params.size;  
        const endIndex = Math.min(startIndex + params.size, totalCount);  
        const paginatedData = response.data.data.slice(startIndex, endIndex);  
    
        // 格式化分页后的数据  
        const formattedData = paginatedData.map(item => ({  
            type: item.type, // 将 userName 映射为 number  
            time: item.time.replace('T', ' '), // 替换loginTime中的'T'为一个空格  
            title: item.info,
        }));  
  
        // 返回一个包含数据和条数的对象  
        return {  
            data: formattedData,  
            totalCount: totalCount,  
        }; 
    })  
    .catch(error => {  
        // 处理错误  
        console.error('请求失败:', error);  
    });  
}
export function getOutTime(){
    return request({
        url: 'user/getOutTime',
        method: 'Get'
    }) 
}
export function deleteLog(params){
    console.log(params);
    const queryParams = [];  

    if (params.type !== '') {  
        queryParams.push(`type=${encodeURIComponent(params.type)}`);  
    }  
    if (params.time !== ''){
        queryParams.push(`time=${encodeURIComponent(params.time)}`);  
    }
    if (params.title !== ''){
        queryParams.push(`title=${encodeURIComponent(params.title)}`);  
    }
    console.log(params.type);
    console.log(params.title);
    console.log(params.time);
    const queryString = queryParams.length > 0 ? `?${queryParams.join('&')}` : ''; 

    const baseUrl = '/logs/deleteLog';  
    const url = baseUrl + queryString; 
    return request({
        url: url,
        method: 'Get'
    })
    .then(response => {  
         // 处理响应  
         console.log('删除成功:', response);  
         // 可以根据需要返回响应数据或执行其他操作  
         return response; // 返回响应给调用者
    
    })  
    .catch(error => {  
        // 处理错误  
        console.error('请求失败:', error);  
    });  
}

export function deleteLogByName(params){
    console.log(params);
    const queryParams = [];  
    if(params.number!==''){
        queryParams.push(`number=${encodeURIComponent(params.number)}`);  
    }
    if (params.type !== '') {  
        queryParams.push(`type=${encodeURIComponent(params.type)}`);  
    }  
    if (params.time !== ''){
        queryParams.push(`time=${encodeURIComponent(params.time)}`);  
    }
    if (params.title !== ''){
        queryParams.push(`title=${encodeURIComponent(params.title)}`);  
    }
    console.log(params.type);
    console.log(params.title);
    console.log(params.time);
    const queryString = queryParams.length > 0 ? `?${queryParams.join('&')}` : ''; 

    const baseUrl = '/logs/deleteLogByName';  
    const url = baseUrl + queryString; 
    return request({
        url: url,
        method: 'Get'
    })
    .then(response => {  
         // 处理响应  
         console.log('删除成功:', response);  
         // 可以根据需要返回响应数据或执行其他操作  
         return response; // 返回响应给调用者
    
    })  
    .catch(error => {  
        // 处理错误  
        console.error('请求失败:', error);  
    });  
}