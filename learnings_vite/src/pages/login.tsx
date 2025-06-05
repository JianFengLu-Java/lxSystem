import {Input} from "@heroui/input";
import {Button} from "@heroui/button";
import {Card, CardBody, CardHeader, Divider, Listbox, Progress} from "@heroui/react";
import {useEffect, useRef, useState} from "react";
import ChatBubble from "@/pages/chatBubble.tsx";


export const Login = ()=>{

    const bottom = useRef<HTMLDivElement|null>(null);
    const [count, setCount] = useState(1)
    const [messages, setMessage] = useState([]);
    const [input, setInput] = useState('')
    const handleSender = ()=>{
        if(input.trim() === '') return;

        const newMessage = {
            id:count+1,
            sender:'user',
            avatar:'https://http.cat/200',
            message:input,
            timestamp:'未读',
        }
        setMessage([...messages,newMessage]);
        setInput('')

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


                    <div className={'flex flex-col h-screen'}>
                        <div className={'h-20'}></div>
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

                        <div className={' flex mb-1 items-center space-x-3 pl-1 pr-1'}>

                            <Input onChange={(e) => setInput(e.target.value)}
                                   onKeyDown={(e) => {
                                       if (e.key === 'Enter') {
                                           handleSender()
                                       }
                                   }}
                                   value={input} label={'message'} radius={'sm'} variant={'flat'} size={'md'}/>

                            <Button
                                className={'bg-blue-500 text-white font-bold border-t-1 border-b-1 border-t-blue-50 border-opacity-65 border-b-blue-900'}
                                size={'lg'} onPress={handleSender}>发送</Button>
                        </div>
                    </div>

                </div>
                <div className={'h-dvh bg-blue-100 flex-[2]'}>

                </div>

            </div>

        </>
    )
}