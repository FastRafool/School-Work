import { useState } from "react";
import {
    Button,
    Container,
    Header,
    Input,
    Select,
    SelectProps,
    SpaceBetween,
    BreadcrumbGroup,
    ContentLayout,
} from "@cloudscape-design/components";
import BaseAppLayout from "../../../components/base-app-layout";
import { useOnFollow } from "../../../common/hooks/use-on-follow";
import { APP_NAME } from "../../../common/constants";

function NewsletterPage() {
    const [selectedOption, setSelectedOption] = useState<SelectProps.Option | null>(null);
    const [email, setEmail] = useState("");

    const dropdownOptions: SelectProps.Options = [
        {
            label: "Group label",
            options: [
                { label: "Option item 1", value: "option1" },
                { label: "Option item 2", value: "option2" },
                { label: "Option item 3", value: "option3" },
            ],
        },
    ];

    const onFollow = useOnFollow();

    return (
        <BaseAppLayout
            breadcrumbs={
                <BreadcrumbGroup
                    onFollow={onFollow}
                    items={[
                        {
                            text: APP_NAME,
                            href: "/",
                        },
                        {
                            text: "Newsletter",
                            href: "/section2/item1",
                        },
                    ]}
                />
            }
            content={
                <ContentLayout header={<Header>Newsletter</Header>}>
                    <SpaceBetween size="l">
                        <Header
                            variant="h1"
                            description="Get daily, weekly, or monthly updates on mentions of AWS Braket from Arxiv right in your mailbox."
                        >
                            GET OUR NEWSLETTER!
                        </Header>
                        <Container>
                            <SpaceBetween size="m">
                                <Input
                                    type="email"
                                    placeholder="Email Address"
                                    value={email}
                                    onChange={(event) => setEmail(event.detail.value)}
                                />
                                <Button variant="primary">SUBSCRIBE</Button>
                                <Select
                                    placeholder="Choose options"
                                    selectedOption={selectedOption}
                                    onChange={(event) => setSelectedOption(event.detail.selectedOption)}
                                    options={dropdownOptions}
                                />
                            </SpaceBetween>
                        </Container>
                    </SpaceBetween>
                </ContentLayout>
            }
        />
    );
}

export default NewsletterPage;
