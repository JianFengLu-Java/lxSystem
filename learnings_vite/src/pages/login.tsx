import {Input} from "@heroui/input";
import {Button} from "@heroui/button";
import {Card, CardBody, CardHeader, Divider, Listbox, Progress} from "@heroui/react";
import {useEffect, useRef, useState} from "react";
import ChatBubble from "@/pages/chatBubble.tsx";
import WebSocketService from "@/utils/ws.ts";


export const CameraIcon = ({fill = "currentColor", size, height, width, ...props}) => {
    return (
        <svg
            fill="none"
            height={size || height || 24}
            viewBox="0 0 24 24"
            width={size || width || 24}
            xmlns="http://www.w3.org/2000/svg"
            {...props}
        >
            <path
                clipRule="evenodd"
                d="M17.44 6.236c.04.07.11.12.2.12 2.4 0 4.36 1.958 4.36 4.355v5.934A4.368 4.368 0 0117.64 21H6.36A4.361 4.361 0 012 16.645V10.71a4.361 4.361 0 014.36-4.355c.08 0 .16-.04.19-.12l.06-.12.106-.222a97.79 97.79 0 01.714-1.486C7.89 3.51 8.67 3.01 9.64 3h4.71c.97.01 1.76.51 2.22 1.408.157.315.397.822.629 1.31l.141.299.1.22zm-.73 3.836c0 .5.4.9.9.9s.91-.4.91-.9-.41-.909-.91-.909-.9.41-.9.91zm-6.44 1.548c.47-.47 1.08-.719 1.73-.719.65 0 1.26.25 1.72.71.46.459.71 1.068.71 1.717A2.438 2.438 0 0112 15.756c-.65 0-1.26-.25-1.72-.71a2.408 2.408 0 01-.71-1.717v-.01c-.01-.63.24-1.24.7-1.699zm4.5 4.485a3.91 3.91 0 01-2.77 1.15 3.921 3.921 0 01-3.93-3.926 3.865 3.865 0 011.14-2.767A3.921 3.921 0 0112 9.402c1.05 0 2.04.41 2.78 1.15.74.749 1.15 1.738 1.15 2.777a3.958 3.958 0 01-1.16 2.776z"
                fill={fill}
                fillRule="evenodd"
            />
        </svg>
    );
};

export const Login = ()=>{
    const [messages, setMessage] = useState([]);

    useEffect(() => {
        const ws = WebSocketService.getWebSocketInstance()
        ws.connect('ws://localhost:8080/ws/chat')
        ws.onMessage(event => {
            console.log(event)
            const newMsg = {
                id:count+1,
                avatar: 'https://http.cat/404',
                message: event.data,
                sender: 'server',
                timestamp: '服务器',
            }
            setMessage((prev)=>[...prev,newMsg])
        })

        return ()=>{
            ws.close();
        }
    }, []);

    const bottom = useRef<HTMLDivElement|null>(null);
    const [count, setCount] = useState(1)
    const [input, setInput] = useState('')
    const [enable, setEnable] = useState(true);
    const handleSender = ()=>{
        if(input.trim() === '') return;

        const newMessage = {
            id:count+1,
            sender:'user',
            avatar:'https://http.cat/200',
            message:input,
            timestamp:'未读',
        }
        WebSocketService.getWebSocketInstance().send(input);

        setMessage([...messages,newMessage]);
        setInput('')
        setEnable(true)


    }
    useEffect(() => {
        bottom.current?.scrollIntoView({ behavior: "smooth" });
    }, [messages]); // 每次消息更新触发




    return(
        <>
            <div className={'fixed  w-dvw  flex '}>

                <div className={'h-screen bg-blue-100 flex-[2]'}>

                </div>
                <div className={'h-screen  flex-[6]'}>


                    <div className={'flex flex-col h-screen w-100'}>
                        <div className={'h-20'}></div>
                        <Divider/>
                        <div className={'flex-1  overflow-y-auto'}>
                            {messages.map((msg) => {
                                // @ts-ignore
                                return (
                                    <div className={'mb-2 pl-4'}>
                                        <ChatBubble
                                            avatar={msg.avatar}
                                            sender={msg.sender}
                                            message={msg.message}
                                            timestamp={msg.timestamp}


                                        ></ChatBubble>
                                    </div>

                                )
                            })}
                            <div ref={bottom}></div>
                        </div>
                        <Divider/>

                        <div className={'m-4 bottom-1 ml-2 mr-2  flex  items-center space-x-3 pl-1 pr-1'}>

                            <Input onChange={(e) =>{ setInput(e.target.value)
                                setEnable(false)
                            }}
                                   onKeyDown={(e) => {
                                       if (e.key === 'Enter') {
                                           handleSender()
                                       }
                                   }}
                                   value={input} label={'message'} radius={'sm'} variant={'flat'} size={'sm'}/>

                            <Button isIconOnly={true} variant={'ghost'}>
                                <CameraIcon size={undefined} height={undefined} width={undefined}/>
                            </Button>

                            <Button
                                isDisabled={enable}
                                className={'bg-blue-500 text-white font-bold border-t-1 border-b-1 border-t-blue-50 border-opacity-65 border-b-blue-900'}
                                size={'md'} onPress={handleSender}>发送</Button>
                        </div>
                    </div>

                </div>
                <div className={'h-dvh bg-blue-100 flex-[2]'}>

                </div>

            </div>

        </>
    )
}