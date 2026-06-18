// index.js

export const formData = {
    form: {
      port: 22, // 端口
      listenAddress: "0.0.0.0", // IPv4
      RootLogin: '', // 允许SSH登录
      PasswordAuth: '', // 启用密码认证
    },
    setForm: {
      port: '22',
      address: '',
      root: '',
      password: '',
    },
    portVisible: false,
    addressVisible: false,
    rootVisible: false,
    passwordVisible: false,
  };
  
  export const codeData = {
    code: '请输入命令', // 用户输入的代码
  };