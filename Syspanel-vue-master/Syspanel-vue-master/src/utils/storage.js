export default {  
    setItem(key, value) {  
        // 使用临时变量来避免修改传入的参数  
        const valueString = JSON.stringify(value);  
        window.localStorage.setItem(key, valueString);  
    },  
    getItem(key, defaultValue = null) { // 为 defaultValue 提供一个默认值，避免未定义的情况  
        let value = window.localStorage.getItem(key);  
        try {  
            if (value !== null) { // 确保 value 不是 null（localStorage.getItem 返回 null 表示未找到项）  
                value = JSON.parse(value);  
            }  
        } catch (error) {  
            console.error(`Error parsing localStorage item "${key}":`, error); // 记录错误以便于调试  
            return defaultValue; // 在解析失败时返回默认值  
        }  
        return value || defaultValue; // 如果 value 是 falsy（包括 null、undefined、"" 等），则返回 defaultValue  
    },  
    removeItem(key) {  
        window.localStorage.removeItem(key);  
    },  
    clear() {  
        window.localStorage.clear();  
    }  
};