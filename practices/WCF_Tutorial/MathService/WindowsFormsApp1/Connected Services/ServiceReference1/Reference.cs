﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace WindowsFormsApp1.ServiceReference1 {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(ConfigurationName="ServiceReference1.IService1")]
    public interface IService1 {
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IService1/Add", ReplyAction="http://tempuri.org/IService1/AddResponse")]
        int Add(int v1, int v2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IService1/Add", ReplyAction="http://tempuri.org/IService1/AddResponse")]
        System.Threading.Tasks.Task<int> AddAsync(int v1, int v2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IService1/Mult", ReplyAction="http://tempuri.org/IService1/MultResponse")]
        int Mult(int v1, int v2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IService1/Mult", ReplyAction="http://tempuri.org/IService1/MultResponse")]
        System.Threading.Tasks.Task<int> MultAsync(int v1, int v2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IService1/Sub", ReplyAction="http://tempuri.org/IService1/SubResponse")]
        int Sub(int v1, int v2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IService1/Sub", ReplyAction="http://tempuri.org/IService1/SubResponse")]
        System.Threading.Tasks.Task<int> SubAsync(int v1, int v2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IService1/Div", ReplyAction="http://tempuri.org/IService1/DivResponse")]
        int Div(int v1, int v2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IService1/Div", ReplyAction="http://tempuri.org/IService1/DivResponse")]
        System.Threading.Tasks.Task<int> DivAsync(int v1, int v2);
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface IService1Channel : WindowsFormsApp1.ServiceReference1.IService1, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class Service1Client : System.ServiceModel.ClientBase<WindowsFormsApp1.ServiceReference1.IService1>, WindowsFormsApp1.ServiceReference1.IService1 {
        
        public Service1Client() {
        }
        
        public Service1Client(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public Service1Client(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public Service1Client(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public Service1Client(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        public int Add(int v1, int v2) {
            return base.Channel.Add(v1, v2);
        }
        
        public System.Threading.Tasks.Task<int> AddAsync(int v1, int v2) {
            return base.Channel.AddAsync(v1, v2);
        }
        
        public int Mult(int v1, int v2) {
            return base.Channel.Mult(v1, v2);
        }
        
        public System.Threading.Tasks.Task<int> MultAsync(int v1, int v2) {
            return base.Channel.MultAsync(v1, v2);
        }
        
        public int Sub(int v1, int v2) {
            return base.Channel.Sub(v1, v2);
        }
        
        public System.Threading.Tasks.Task<int> SubAsync(int v1, int v2) {
            return base.Channel.SubAsync(v1, v2);
        }
        
        public int Div(int v1, int v2) {
            return base.Channel.Div(v1, v2);
        }
        
        public System.Threading.Tasks.Task<int> DivAsync(int v1, int v2) {
            return base.Channel.DivAsync(v1, v2);
        }
    }
}
