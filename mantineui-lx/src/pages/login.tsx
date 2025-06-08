import {
    Avatar, Badge, Box, Button,
    Card, Checkbox, Chip,
    Container,
    createTheme,
    Flex,
    Grid, Image,
    Input,
    MantineProvider, Paper,
    PasswordInput, SegmentedControl,
    Text, TextInput,
    Title
} from "@mantine/core";
import classes from "../Button.module.css";
import {notifications} from "@mantine/notifications";
import {useState} from "react";
import {QRCodeCanvas} from "qrcode.react";

/*
* @param title 标题
* @param describe 小标题
* */

// @ts-ignore
const Login=({title,describe})=>{

    const [value, setValue] = useState('access')

    const theme = createTheme({
        cursorType: 'pointer',

    });

    return (
        <>
            <Container size={'500px'}>
                <Flex mih={180} h={'100vh'} justify={'center'} align={'center'} direction={'column'} wrap={'wrap'}>
                    <Card mah={'650px'} shadow={'lg'} withBorder={true}  miw={'500px'} radius={'lg'}>
                        <Card.Section withBorder>
                            <Title order={4} m={20} mb={5}>
                                {title}
                            </Title>
                            <Text size={'12px'} pl={20} mb={10} color={'#b5a4a4'}>
                                {describe}
                            </Text>
                        </Card.Section>
                        <SegmentedControl
                            mt={'24'}
                            mb={"10"}
                            size={'sm'}
                            value={value}
                            onChange={
                                setValue
                        }
                            data={[
                            {label:'账号密码登陆',value:'access'},
                            {label: '扫码登陆',value: 'qr'}
                        ]}
                            radius={'md'}
                        />
                        {value === 'qr' &&
                        <>
                            <Grid justify={'center'} align={'center'} >

                                    <Box p={20}   m={10} >
                                        <div style={{borderRadius:10,border:'1px solid rgba(0,0,0,0.1)',borderColor:'rgba(0,0,0,0.1)' ,padding:10}} >
                                            <QRCodeCanvas value={'http://localhost:8080/'} size={120} bgColor={'rgba(0,0,0,0)'} level={'Q'}/>

                                        </div>

                                    </Box>
                                    <Badge fullWidth={true} color={'blue'}>使用飞书扫描二维码</Badge>


                            </Grid>
                        </>
                        }
                        {value === 'access' &&
                            <><Flex direction={'column'}>
                                <Grid grow={true} p={20}>
                                    <Grid.Col span={1} >
                                        <Flex  align={'center'} h={'100px'}>
                                            <Avatar  draggable={false} size={"xl"} src={'http://localhost:8080/img/1.jp'}>
                                                LX
                                            </Avatar>
                                        </Flex>

                                    </Grid.Col>
                                    <Grid.Col span={6}>
                                        <Flex direction={"column"} gap={16}>
                                            <TextInput  size={'md'} style={{fontWeight: 'bold'}} placeholder={'输入账号'} radius={'lg'}/>
                                            <PasswordInput  size={'md'} style={{fontWeight: 'bold'}} placeholder={"输入密码"}
                                                            radius={'lg'}/>
                                        </Flex>


                                    </Grid.Col>
                                </Grid>




                                <Flex>
                                    <MantineProvider theme={theme}>
                                        <Checkbox label={'记住我'}/>

                                    </MantineProvider>
                                </Flex>
                                <Button radius={'lg'} color={'yellow'} size={'md'} onClick={() => {
                                    notifications.show({
                                        title: 'hello',
                                        message: 'Gogogo',
                                        radius: 'lg',
                                        withBorder: true
                                    });
                                }}>登 陆</Button>
                            </Flex>
                            </>
                        }

                    </Card>

                </Flex>
            </Container>
        </>)
}
export default Login;