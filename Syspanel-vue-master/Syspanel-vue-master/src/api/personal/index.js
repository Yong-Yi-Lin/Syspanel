
export function personalFun(data) {
   
    return new Promise((resolve) => {
        localStorage.setItem('personalInfo', JSON.stringify(JSON.stringify(data)));
        resolve();
    });
}