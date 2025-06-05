import {Avatar, Card, CardHeader, Tooltip} from "@heroui/react";
import {Button} from "@heroui/button";

const ChatBubble = (
    {avatar, message, timestamp, sender,})=>{
    const isUser:Boolean = sender === 'user'

    return(
        <>
            <div className={' relative max-w-xl  h-fit flex space-x-2'}>
                {isUser && <div className={' items-center justify-center space-y-1-3 pt-2 min-w-10   h-full'}>
                    <div className={'grid place-items-center '}>
                        <Tooltip  delay={1000} content={<>
                            <Button size={'sm'} startContent={<p>+</p>} radius={"full"}>关注</Button>
                        </>}>
                            <Avatar className={'cursor-pointer'} src={avatar} size={'md'}/>
                        </Tooltip>

                    </div>

                </div>}
                <div className={' h-full  rounded-bl-md w-auto'}>
                    <span className={'text-zinc-400 text-[12px]'}>{sender}</span>
                    <div
                        className={'bg-gradient-to-br from-pink-50 to-blue-100  p-2 rounded-xl  border-1  border-zinc-200   transition ease-in-out duration-[1500ms]'}>
                        <p className={'text-[15px] '}>
                            {message}
                        </p>
                    </div>
                </div>

                <span>
                    <p className={'absolute text-[12px] text-zinc-400 bottom-1'}>
                        {timestamp}
                    </p>
                </span>
            </div>
        </>
    )
}
export default ChatBubble;