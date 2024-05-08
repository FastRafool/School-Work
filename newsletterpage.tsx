import { useState } from "react";
import {
    Button,
    Container,
    Header,
    Input,
    Multiselect,
    MultiselectProps,
    BreadcrumbGroup,
    Box,
    Layout,  // Assuming this is your intended component, replace if it's different
} from "@cloudscape-design/components";
import BaseAppLayout from "../../../components/base-app-layout";
import { useOnFollow } from "../../../common/hooks/use-on-follow";
import { APP_NAME } from "../../../common/constants";

function NewsletterPage() {
    const [selectedOptions, setSelectedOptions] = useState<MultiselectProps.Option[]>([]);
    const [email, setEmail] = useState("");

    const dropdownOptions: MultiselectProps.Options = [
        {
            label: "Quantum Computing",
            options: [
                { label: "Amazon Braket", value: "braket" },
                { label: "Azure Quantum", value: "azure-quantum" },
                { label: "Oxford Quantum Circuits", value: "oxford" },
            ],
        },
        {
            label: "Machine Learning",
            options: [
                { label: "TensorFlow", value: "tensorflow" },
                { label: "PyTorch", value: "pytorch" },
                { label: "Scikit-Learn", value: "scikit-learn" },
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
                <Layout header={<Header>Newsletter</Header>}>
                    <Container>
                        <Header
                            variant="h1"
                            description="Get daily, weekly, or monthly updates on mentions of AWS Braket from Arxiv right in your mailbox."
                        >
                            GET OUR NEWSLETTER!
                        </Header>
                        <Box padding={{ vertical: 's', horizontal: 'm' }}>
                            <p><strong>Frequency and Keyword options for your emails.</strong></p>
                            <p>This will give you a choice of what content you receive and how often you will receive emails from us.</p>
                        </Box>
                        <Multiselect
                            placeholder="Choose options"
                            selectedOptions={selectedOptions}
                            onChange={(event) =>
                                setSelectedOptions([...event.detail.selectedOptions] as MultiselectProps.Option[])
                            }
                            options={dropdownOptions}
                        />
                        <Input
                            type="email"
                            placeholder="Email Address"
                            value={email}
                            onChange={(event) => setEmail(event.detail.value)}
                        />
                        <Button variant="primary">SUBSCRIBE</Button>
                    </Container>
                </Layout>
            }
        />
    );
}

export default NewsletterPage;
