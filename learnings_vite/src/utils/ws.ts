class WebSocketService {
    private constructor() {
    }
    private static instance: WebSocketService;
    private socket: WebSocket | null = null;
    private listeners: ((data: MessageEvent) => void)[] = [];

    public static getWebSocketInstance():WebSocketService{
        if(!WebSocketService.instance){
            WebSocketService.instance = new WebSocketService();

        }
        return WebSocketService.instance;
    }

    public connect(url: string): void {
        if (this.socket) return; // 已连接

        this.socket = new WebSocket(url);

        this.socket.onopen = () => {
            console.log("✅ WebSocket connected");
        };

        this.socket.onmessage = (event) => {
            this.listeners.forEach((cb) => cb(event));
        };

        this.socket.onclose = () => {
            console.log("🔌 WebSocket closed");
            this.socket = null;
        };

        this.socket.onerror = (e) => {
            console.error("❌ WebSocket error", e);
        };
    }
    public send(data: string | object): void {
        if (!this.socket || this.socket.readyState !== WebSocket.OPEN) {
            console.warn("🚫 WebSocket is not open yet.");
            return;
        }
        const payload = typeof data === "string" ? data : JSON.stringify(data);
        this.socket.send(payload);
    }

    public onMessage(callback: (event: MessageEvent) => void): void {
        this.listeners.push(callback);
    }

    public close(): void {
        this.socket?.close();
        this.socket = null;
        this.listeners = [];
    }

}

export default WebSocketService;